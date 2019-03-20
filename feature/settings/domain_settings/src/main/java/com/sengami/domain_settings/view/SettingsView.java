package com.sengami.domain_settings.view;

import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;

import org.jetbrains.annotations.NotNull;

import io.reactivex.subjects.Subject;

public interface SettingsView extends WithErrorHandler, WithLoadingIndicator {

    @NotNull
    Subject<Boolean> getCreateBackupTrigger();

    @NotNull
    Subject<Boolean> getRestoreBackupTrigger();

    @NotNull
    Subject<Boolean> getExportToTextFileTrigger();
}