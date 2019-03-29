package com.sengami.domain_settings.operation;

import com.sengami.domain_base.operation.Operation;

import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;

public interface CreateBackupOperation extends Operation<Boolean> {

    @NotNull
    CreateBackupOperation withBackupFileOutputStream(@NotNull final OutputStream backupFileOutputStream);
}