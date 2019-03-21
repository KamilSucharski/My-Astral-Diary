package com.sengami.domain_settings.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_settings.operation.CreateBackupOperation;
import com.sengami.domain_settings.operation.ExportToTextFileOperation;
import com.sengami.domain_settings.operation.RestoreFromBackupOperation;
import com.sengami.domain_settings.view.SettingsView;

import org.jetbrains.annotations.NotNull;

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
    protected void onSubscribe(@NotNull final SettingsView view) {
        subscribeCreateBackupTrigger(view);
        subscribeRestoreFromBackupTrigger(view);
        subscribeExportToTextFileTrigger(view);
    }

    private void subscribeCreateBackupTrigger(@NotNull final SettingsView view) {
        disposables.add(
            view.getCreateBackupTrigger()
                .flatMap(x -> createBackupOperation.execute())
                .subscribe(view::showSavedFile)
        );
    }

    private void subscribeRestoreFromBackupTrigger(@NotNull final SettingsView view) {
        disposables.add(
            view.getRestoreFromBackupTrigger()
                .flatMap(file -> restoreFromBackupOperation.withBackupFile(file).execute())
                .subscribe(x -> view.refreshApplication())
        );
    }

    private void subscribeExportToTextFileTrigger(@NotNull final SettingsView view) {
        disposables.add(
            view.getExportToTextFileTrigger()
                .flatMap(x -> exportToTextFileOperation.execute())
                .subscribe(view::showSavedFile)
        );
    }
}