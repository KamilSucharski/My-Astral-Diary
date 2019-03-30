package com.sengami.gui_base.database;

import android.content.Context;

import com.sengami.data_base.util.DatabaseConnectionProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DatabaseConnectionProviderHolder {

    @Nullable
    private static DatabaseConnectionProvider databaseConnectionProvider;

    public static void initialize(@NotNull final Context context) {
        DatabaseConnectionProviderHolder.databaseConnectionProvider = new AndroidDatabaseConnectionProvider(context);
    }

    @NotNull
    public static DatabaseConnectionProvider provide() {
        if (databaseConnectionProvider == null) {
            throw new RuntimeException("[DatabaseConnectionProvider databaseConnectionProvider] not set in DatabaseConnectionProviderHolder");
        }

        return databaseConnectionProvider;
    }
}