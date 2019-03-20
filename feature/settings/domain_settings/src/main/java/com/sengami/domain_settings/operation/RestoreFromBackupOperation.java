package com.sengami.domain_settings.operation;

import com.sengami.domain_base.operation.Operation;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface RestoreFromBackupOperation extends Operation<Boolean> {

    @NotNull
    RestoreFromBackupOperation withBackupFile(@NotNull final File backup);
}