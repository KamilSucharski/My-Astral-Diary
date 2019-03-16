package com.sengami.gui_base.di.module;

import android.content.Context;

import com.sengami.data_base.util.ConnectionSourceProvider;
import com.sengami.gui_base.util.AndroidConnectionSourceProvider;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class ConnectionSourceProviderModule {

    @Provides
    @NotNull
    ConnectionSourceProvider connectionSourceProvider(@NotNull final Context context) {
        return new AndroidConnectionSourceProvider(context);
    }
}