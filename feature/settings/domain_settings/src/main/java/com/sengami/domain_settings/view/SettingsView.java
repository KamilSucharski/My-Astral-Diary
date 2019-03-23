package com.sengami.domain_settings.view;

import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import io.reactivex.Observable;

public interface SettingsView extends WithErrorHandler, WithLoadingIndicator {

    @NotNull
    Observable<Boolean> getCreateBackupTrigger();

    @NotNull
    Observable<File> getRestoreFromBackupTrigger();

    @NotNull
    Observable<Boolean> getExportToTextFileTrigger();

    void showSavedFile(@NotNull final File file);

    void refreshApplication();
}