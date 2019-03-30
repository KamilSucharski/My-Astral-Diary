package com.sengami.data_base.util;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface DatabaseFileProvider {

    @NotNull
    File provide(@NotNull final String databaseName);
}