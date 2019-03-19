package com.sengami.error_handler.di.module;

import com.sengami.domain_base.error.WithErrorHandler;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class WithErrorHandlerModule {

    @NotNull
    private final WithErrorHandler withErrorHandler;

    public WithErrorHandlerModule(@NotNull final WithErrorHandler withErrorHandler) {
        this.withErrorHandler = withErrorHandler;
    }

    @Provides
    @NotNull
    WithErrorHandler withErrorHandler() {
        return withErrorHandler;
    }
}