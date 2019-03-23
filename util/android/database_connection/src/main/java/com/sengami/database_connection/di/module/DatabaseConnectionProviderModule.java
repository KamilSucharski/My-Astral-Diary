package com.sengami.database_connection.di.module;

import android.content.Context;

import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.database_connection.implementation.AndroidDatabaseConnectionProvider;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class DatabaseConnectionProviderModule {

    @Provides
    @NotNull
    DatabaseConnectionProvider databaseConnectionProvider(@NotNull final Context context) {
        return new AndroidDatabaseConnectionProvider(context);
    }
}