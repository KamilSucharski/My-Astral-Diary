package com.sengami.domain_settings.operation;

import com.sengami.domain_base.operation.Operation;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public interface RestoreFromBackupOperation extends Operation<Boolean> {

    @NotNull
    RestoreFromBackupOperation withBackupFileInputStream(@NotNull final InputStream backupFileInputStream);
}