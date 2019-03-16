package com.sengami.domain_base.util;

import org.jetbrains.annotations.NotNull;

public interface ErrorHandler {

    void handleError(@NotNull final Throwable throwable);
}