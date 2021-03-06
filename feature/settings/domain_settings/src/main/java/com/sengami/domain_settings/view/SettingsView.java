package com.sengami.domain_settings.view;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;

public interface SettingsView {

    @NotNull
    Observable<OutputStream> getCreateBackupTrigger();

    @NotNull
    Observable<InputStream> getRestoreFromBackupTrigger();

    @NotNull
    Observable<OutputStream> getExportToTextFileTrigger();

    void onFileSaved();

    void refreshApplication();
}