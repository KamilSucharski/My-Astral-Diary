package com.sengami.android_database;

import android.content.Context;

import com.sengami.data_base.util.DatabaseFileProvider;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class AndroidDatabaseFileProvider implements DatabaseFileProvider {

    @NotNull
    private final Context context;

    @NotNull
    public AndroidDatabaseFileProvider(@NotNull final Context context) {
        this.context = context;
    }

    @Override
    @NotNull
    public File provide(@NotNull final String databaseName) {
        return context.getDatabasePath(databaseName);
    }
}