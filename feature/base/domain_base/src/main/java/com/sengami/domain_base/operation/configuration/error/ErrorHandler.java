package com.sengami.domain_base.operation.configuration.error;

import org.jetbrains.annotations.NotNull;

public interface ErrorHandler {

    void handleError(@NotNull final Throwable throwable);
}