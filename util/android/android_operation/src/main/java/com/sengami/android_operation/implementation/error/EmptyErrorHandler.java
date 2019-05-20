package com.sengami.android_operation.implementation.error;

import com.sengami.domain_base.operation.configuration.error.ErrorHandler;

import org.jetbrains.annotations.NotNull;

public final class EmptyErrorHandler implements ErrorHandler {

    @Override
    public void handleError(@NotNull final Throwable throwable) {
        //no-op
    }
}