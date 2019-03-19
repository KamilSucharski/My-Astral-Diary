package com.sengami.context.di.module;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class ContextModule {

    @NotNull
    private final Context context;

    public ContextModule(@NotNull final Context context) {
        this.context = context;
    }

    @Provides
    @NotNull
    Context context() {
        return context;
    }
}