package com.sengami.di_base.module;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class ContextModule {

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