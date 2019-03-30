package com.sengami.gui_base.database;

import com.sengami.data_base.util.DatabaseConnectionProvider;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class DatabaseConnectionProviderModule {

    @Provides
    @NotNull
    DatabaseConnectionProvider databaseConnectionProvider() {
        return DatabaseConnectionProviderHolder.provide();
    }
}