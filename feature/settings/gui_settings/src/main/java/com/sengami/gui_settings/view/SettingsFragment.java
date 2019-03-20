package com.sengami.gui_settings.view;

import android.Manifest;
import android.content.Context;
import android.widget.Toast;

import com.sengami.context.di.module.ContextModule;
import com.sengami.domain_base.error.ErrorHandler;
import com.sengami.domain_base.loading.LoadingIndicator;
import com.sengami.domain_settings.presenter.SettingsPresenter;
import com.sengami.domain_settings.view.SettingsView;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.error_handler.implementation.ToastErrorHandler;
import com.sengami.gui_base.BaseFragment;
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
import com.sengami.util_loading_indicator.di.module.WithLoadingIndicatorModule;
import com.sengami.util_loading_indicator.implementation.ViewVisibilityLoadingIndicator;

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
    extends BaseFragment<SettingsPresenter, FragmentSettingsBinding>
    implements SettingsView, SettingsListCallbacks {

    private static final String STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private final Subject<Boolean> createBackupTrigger = PublishSubject.create();
    private final Subject<File> restoreFromBackupTrigger = PublishSubject.create();
    private final Subject<Boolean> exportToTextFileTrigger = PublishSubject.create();
    private ErrorHandler errorHandler;
    private LoadingIndicator loadingIndicator;

    @Inject
    @Override
    protected void injectPresenter(@NotNull final SettingsPresenter presenter) {
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
        setupList(context);
        errorHandler = new ToastErrorHandler(context);
        loadingIndicator = new ViewVisibilityLoadingIndicator(binding.loadingWheelOverlay);
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
    public void saveFile(@NotNull final File file) {
        Toast.makeText(getContext(), "Created file", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshApplication() {
        Toast.makeText(getContext(), "Refresh app", Toast.LENGTH_SHORT).show();
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
        Permissions.withPermission(getContext(), STORAGE_PERMISSION, () -> createBackupTrigger.onNext(true));
    }

    @Override
    public void onRestoreFromBackupClicked() {
        Permissions.withPermission(getContext(), STORAGE_PERMISSION, () -> {});
    }

    @Override
    public void onExportToTextFileClicked() {
        Permissions.withPermission(getContext(), STORAGE_PERMISSION, () -> exportToTextFileTrigger.onNext(true));
    }

    private void setupList(@NotNull final Context context) {
        final BaseAdapter<SettingsListElement, SettingsListElementType> adapter = new SettingsListAdapter(context);
        final ElementConverter<SettingsListCallbacks, SettingsListElement> converter = new SettingsListElementConverter();
        final List<SettingsListElement> elements = converter.convert(this);
        adapter.addAll(elements);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(adapter);
    }
}