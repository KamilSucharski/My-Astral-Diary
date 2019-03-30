package com.sengami.gui_settings.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.jakewharton.processphoenix.ProcessPhoenix;
import com.sengami.android_database.di.module.DatabaseFileProviderModule;
import com.sengami.android_operation.di.module.WithErrorHandlerModule;
import com.sengami.android_operation.di.module.WithLoadingIndicatorModule;
import com.sengami.android_operation.implementation.ToastErrorHandler;
import com.sengami.android_operation.implementation.ViewVisibilityLoadingIndicator;
import com.sengami.date.DateFormatter;
import com.sengami.dialogs.message.MessageDialog;
import com.sengami.domain_base.Constants;
import com.sengami.domain_base.operation.error.ErrorHandler;
import com.sengami.domain_base.operation.loading.LoadingIndicator;
import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_settings.view.SettingsView;
import com.sengami.gui_base.navigation.RequestCode;
import com.sengami.gui_base.view.BaseFragment;
import com.sengami.gui_settings.R;
import com.sengami.gui_settings.databinding.FragmentSettingsBinding;
import com.sengami.gui_settings.di.component.DaggerSettingsComponent;
import com.sengami.gui_settings.view.list.adapter.SettingsListAdapter;
import com.sengami.gui_settings.view.list.adapter.SettingsListCallbacks;
import com.sengami.gui_settings.view.list.converter.SettingsListElementConverter;
import com.sengami.gui_settings.view.list.element.SettingsListElement;
import com.sengami.gui_settings.view.list.element.SettingsListElementType;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public final class SettingsFragment
    extends BaseFragment<Presenter<SettingsView>, FragmentSettingsBinding>
    implements SettingsView, SettingsListCallbacks {

    private final Subject<OutputStream> createBackupTrigger = PublishSubject.create();
    private final Subject<InputStream> restoreFromBackupTrigger = PublishSubject.create();
    private final Subject<OutputStream> exportToTextFileTrigger = PublishSubject.create();
    private ErrorHandler errorHandler;
    private LoadingIndicator loadingIndicator;

    @Inject
    @Override
    protected void injectPresenter(@NotNull final Presenter<SettingsView> presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void inject(@NotNull final Context context) {
        DaggerSettingsComponent.builder()
            .databaseFileProviderModule(new DatabaseFileProviderModule(context))
            .withErrorHandlerModule(new WithErrorHandlerModule(this))
            .withLoadingIndicatorModule(new WithLoadingIndicatorModule(this))
            .build()
            .inject(this);
    }

    @Override
    protected void init(@NotNull final Context context) {
        super.init(context);
        errorHandler = new ToastErrorHandler(context);
        loadingIndicator = new ViewVisibilityLoadingIndicator(binding.loadingWheelOverlay);
        setupList(context);
    }

    @Override
    @NotNull
    public Observable<OutputStream> getCreateBackupTrigger() {
        return createBackupTrigger;
    }

    @Override
    @NotNull
    public Observable<InputStream> getRestoreFromBackupTrigger() {
        return restoreFromBackupTrigger;
    }

    @Override
    @NotNull
    public Observable<OutputStream> getExportToTextFileTrigger() {
        return exportToTextFileTrigger;
    }

    @Override
    public void onFileSaved() {
        Toast.makeText(getContext(), R.string.file_saved_successfully, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshApplication() {
        ProcessPhoenix.triggerRebirth(getContext());
    }

    @Override
    @NotNull
    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    @Override
    @NotNull
    public LoadingIndicator getLoadingIndicator() {
        return loadingIndicator;
    }

    @Override
    public void onCreateBackupClicked() {
        final String fileName = String.format(
            Constants.DATABASE_BACKUP_NAME,
            DateFormatter.format(new Date(), Constants.FILE_DATE_FORMAT)
        );
        final Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.setType(Constants.DATABASE_BACKUP_MIME_TYPE);
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(intent, RequestCode.SAVE_BACKUP_FILE.code());
    }

    @Override
    public void onRestoreFromBackupClicked() {
        final Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType(Constants.DATABASE_BACKUP_MIME_TYPE);
        startActivityForResult(intent, RequestCode.LOAD_BACKUP_FILE.code());
    }

    @Override
    public void onExportToTextFileClicked() {
        final String fileName = String.format(
            Constants.TEXT_EXPORT_NAME,
            DateFormatter.format(new Date(), Constants.FILE_DATE_FORMAT)
        );
        final Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.setType(Constants.TEXT_EXPORT_MIME_TYPE);
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(intent, RequestCode.SAVE_TEXT_FILE.code());
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == RequestCode.SAVE_BACKUP_FILE.code()) {
                onBackupFileReadyToSave(data.getData());
                return;
            }

            if (requestCode == RequestCode.LOAD_BACKUP_FILE.code()) {
                onBackupFilePicked(data.getData());
                return;
            }

            if (requestCode == RequestCode.SAVE_TEXT_FILE.code()) {
                onTextExportFileReadyToSave(data.getData());
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupList(@NotNull final Context context) {
        final BaseAdapter<SettingsListElement, SettingsListElementType> adapter = new SettingsListAdapter(context);
        final ElementConverter<SettingsListCallbacks, SettingsListElement> converter = new SettingsListElementConverter();
        final List<SettingsListElement> elements = converter.convert(this);
        adapter.addAll(elements);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(adapter);
    }

    private void onBackupFileReadyToSave(@NotNull final Uri uri) {
        try {
            final OutputStream outputStream = getContext().getContentResolver().openOutputStream(uri);
            createBackupTrigger.onNext(outputStream);
        } catch (final FileNotFoundException e) {
            Log.e(getClass().getSimpleName(), "Failed to create backup file", e);
        }
    }

    private void onBackupFilePicked(@NotNull final Uri uri) {
        try {
            final InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
            new MessageDialog(
                getContext(),
                getContext().getString(R.string.restore_from_backup_warning),
                () -> restoreFromBackupTrigger.onNext(inputStream)
            ).show();
        } catch (final FileNotFoundException e) {
            Log.e(getClass().getSimpleName(), "Failed to create text export file", e);
        }
    }

    private void onTextExportFileReadyToSave(@NotNull final Uri uri) {
        try {
            final OutputStream outputStream = getContext().getContentResolver().openOutputStream(uri);
            exportToTextFileTrigger.onNext(outputStream);
        } catch (final FileNotFoundException e) {
            Log.e(getClass().getSimpleName(), "Failed to create text export file", e);
        }
    }
}