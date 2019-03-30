package com.sengami.android_database.di.module;

import android.content.Context;

import com.sengami.android_database.AndroidDatabaseFileProvider;
import com.sengami.data_base.util.DatabaseFileProvider;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class DatabaseFileProviderModule {

    @NotNull
    private final Context context;

    public DatabaseFileProviderModule(@NotNull final Context context) {
        this.context = context;
    }

    @Provides
    @NotNull
    DatabaseFileProvider databaseFileProvider() {
        return new AndroidDatabaseFileProvider(context);
    }
}