package com.sengami.domain_settings.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_settings.operation.CreateBackupOperation;
import com.sengami.domain_settings.operation.ExportToTextFileOperation;
import com.sengami.domain_settings.operation.RestoreFromBackupOperation;
import com.sengami.domain_settings.view.SettingsView;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public final class SettingsPresenter extends BasePresenter<SettingsView> {

    @NotNull
    private final CreateBackupOperation createBackupOperation;
    @NotNull
    private final RestoreFromBackupOperation restoreFromBackupOperation;
    @NotNull
    private final ExportToTextFileOperation exportToTextFileOperation;

    public SettingsPresenter(@NotNull final CreateBackupOperation createBackupOperation,
                             @NotNull final RestoreFromBackupOperation restoreFromBackupOperation,
                             @NotNull final ExportToTextFileOperation exportToTextFileOperation) {
        this.createBackupOperation = createBackupOperation;
        this.restoreFromBackupOperation = restoreFromBackupOperation;
        this.exportToTextFileOperation = exportToTextFileOperation;
    }

    @Override
    protected List<Disposable> createSubscriptions(@NotNull final SettingsView view) {
        return Arrays.asList(
            subscribeCreateBackupTrigger(view),
            subscribeRestoreFromBackupTrigger(view),
            subscribeExportToTextFileTrigger(view)
        );
    }

    private Disposable subscribeCreateBackupTrigger(@NotNull final SettingsView view) {
        return view
            .getCreateBackupTrigger()
            .flatMap(x -> createBackupOperation.execute())
            .subscribe(view::showSavedFile);
    }

    private Disposable subscribeRestoreFromBackupTrigger(@NotNull final SettingsView view) {
        return view
            .getRestoreFromBackupTrigger()
            .flatMap(file -> restoreFromBackupOperation.withBackupFile(file).execute())
            .subscribe(x -> view.refreshApplication());
    }

    private Disposable subscribeExportToTextFileTrigger(@NotNull final SettingsView view) {
        return view
            .getExportToTextFileTrigger()
            .flatMap(x -> exportToTextFileOperation.execute())
            .subscribe(view::showSavedFile);
    }
}