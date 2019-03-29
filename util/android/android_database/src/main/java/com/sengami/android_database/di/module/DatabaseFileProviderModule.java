package com.sengami.android_database.di.module;

import android.content.Context;

import com.sengami.android_database.AndroidDatabaseFileProvider;
import com.sengami.data_base.util.DatabaseFileProvider;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class DatabaseFileProviderModule {

    @Provides
    @NotNull
    DatabaseFileProvider databaseFileProvider(@NotNull final Context context) {
        return new AndroidDatabaseFileProvider(context);
    }
}