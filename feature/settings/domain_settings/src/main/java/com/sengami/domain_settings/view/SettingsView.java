package com.sengami.domain_settings.view;

import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;

public interface SettingsView extends WithErrorHandler, WithLoadingIndicator {

    @NotNull
    Observable<OutputStream> getCreateBackupTrigger();

    @NotNull
    Observable<InputStream> getRestoreFromBackupTrigger();

    @NotNull
    Observable<OutputStream> getExportToTextFileTrigger();

    void onFileSaved();

    void refreshApplication();
}