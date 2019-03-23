package com.sengami.gui_settings.view;

import android.Manifest;
import android.content.Context;
import android.widget.Toast;

import com.jakewharton.processphoenix.ProcessPhoenix;
import com.sengami.android_operation.di.module.ContextModule;
import com.sengami.android_operation.di.module.WithErrorHandlerModule;
import com.sengami.android_operation.di.module.WithLoadingIndicatorModule;
import com.sengami.android_operation.implementation.ToastErrorHandler;
import com.sengami.android_operation.implementation.ViewVisibilityLoadingIndicator;
import com.sengami.dialogs.file.FilePickerDialog;
import com.sengami.dialogs.message.MessageDialog;
import com.sengami.domain_base.Constants;
import com.sengami.domain_base.operation.error.ErrorHandler;
import com.sengami.domain_base.operation.loading.LoadingIndicator;
import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_settings.view.SettingsView;
import com.sengami.gui_base.view.BaseFragment;
import com.sengami.gui_settings.R;
import com.sengami.gui_settings.databinding.FragmentSettingsBinding;
import com.sengami.gui_settings.di.component.DaggerSettingsComponent;
import com.sengami.gui_settings.view.list.adapter.SettingsListAdapter;
import com.sengami.gui_settings.view.list.adapter.SettingsListCallbacks;
import com.sengami.gui_settings.view.list.converter.SettingsListElementConverter;
import com.sengami.gui_settings.view.list.element.SettingsListElement;
import com.sengami.gui_settings.view.list.element.SettingsListElementType;
import com.sengami.permissions.Permissions;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.io.File;
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

    private static final String STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private final Subject<Boolean> createBackupTrigger = PublishSubject.create();
    private final Subject<File> restoreFromBackupTrigger = PublishSubject.create();
    private final Subject<Boolean> exportToTextFileTrigger = PublishSubject.create();
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
            .contextModule(new ContextModule(context))
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
    public Observable<Boolean> getCreateBackupTrigger() {
        return createBackupTrigger;
    }

    @Override
    @NotNull
    public Observable<File> getRestoreFromBackupTrigger() {
        return restoreFromBackupTrigger;
    }

    @Override
    @NotNull
    public Observable<Boolean> getExportToTextFileTrigger() {
        return exportToTextFileTrigger;
    }

    @Override
    public void showSavedFile(@NotNull final File file) {
        Toast.makeText(getContext(), getString(R.string.saved_file, file.getName()), Toast.LENGTH_SHORT).show();
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
        Permissions.withPermission(
            getContext(),
            STORAGE_PERMISSION, () -> createBackupTrigger.onNext(true)
        );
    }

    @Override
    public void onRestoreFromBackupClicked() {
        Permissions.withPermission(
            getContext(),
            STORAGE_PERMISSION, this::showRestoreFromBackupFilePicker
        );
    }

    @Override
    public void onExportToTextFileClicked() {
        Permissions.withPermission(
            getContext(),
            STORAGE_PERMISSION, () -> exportToTextFileTrigger.onNext(true)
        );
    }

    private void setupList(@NotNull final Context context) {
        final BaseAdapter<SettingsListElement, SettingsListElementType> adapter = new SettingsListAdapter(context);
        final ElementConverter<SettingsListCallbacks, SettingsListElement> converter = new SettingsListElementConverter();
        final List<SettingsListElement> elements = converter.convert(this);
        adapter.addAll(elements);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(adapter);
    }

    private void showRestoreFromBackupFilePicker() {
        new FilePickerDialog(
            getContext(),
            this::showRestoreFromBackupConfirmationDialog,
            Constants.DATABASE_EXTENSION
        ).show();
    }

    private void showRestoreFromBackupConfirmationDialog(@NotNull final File file) {
        new MessageDialog(
            getContext(),
            getContext().getString(R.string.restore_from_backup_warning),
            () -> restoreFromBackupTrigger.onNext(file)
        ).show();
    }
}