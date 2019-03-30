package com.sengami.domain_settings.operation;

import com.sengami.domain_base.operation.Operation;

import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;

public interface ExportToTextFileOperation extends Operation<Boolean> {

    @NotNull
    ExportToTextFileOperation withTextExportFileOutputStream(@NotNull final OutputStream textExportFileOutputStream);
}