package com.sengami.domain_base.util.error;

import org.jetbrains.annotations.NotNull;

public interface ErrorHandler {

    void handleError(@NotNull final Throwable throwable);
}