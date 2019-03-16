package com.sengami.gui_base.di.module;

import com.sengami.domain_base.util.ErrorHandler;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class ErrorHandlerModule {

    private final ErrorHandler errorHandler;

    public ErrorHandlerModule(@NotNull final ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Provides
    @NotNull
    ErrorHandler errorHandler() {
        return errorHandler;
    }
}