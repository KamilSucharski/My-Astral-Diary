package com.sengami.domain_base.operation.error;

import org.jetbrains.annotations.NotNull;

public interface WithErrorHandler {

    @NotNull
    ErrorHandler getErrorHandler();
}