package com.sengami.gui_settings.view.list.converter;

import org.jetbrains.annotations.NotNull;

import io.reactivex.subjects.Subject;

public class SettingsListElementConverterPayload {

    @NotNull
    private final Subject<Boolean> onCreateBackupTrigger;
    @NotNull
    private final Subject<Boolean> onRestoreBackupTrigger;
    @NotNull
    private final Subject<Boolean> onExportToTextFileTrigger;

    public SettingsListElementConverterPayload(@NotNull final Subject<Boolean> onCreateBackupTrigger,
                                               @NotNull final Subject<Boolean> onRestoreBackupTrigger,
                                               @NotNull final Subject<Boolean> onExportToTextFileTrigger) {
        this.onCreateBackupTrigger = onCreateBackupTrigger;
        this.onRestoreBackupTrigger = onRestoreBackupTrigger;
        this.onExportToTextFileTrigger = onExportToTextFileTrigger;
    }

    @NotNull
    public Subject<Boolean> getOnCreateBackupTrigger() {
        return onCreateBackupTrigger;
    }

    @NotNull
    public Subject<Boolean> getOnRestoreBackupTrigger() {
        return onRestoreBackupTrigger;
    }

    @NotNull
    public Subject<Boolean> getOnExportToTextFileTrigger() {
        return onExportToTextFileTrigger;
    }
}