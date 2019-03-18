package com.sengami.domain_base.util.error;

import org.jetbrains.annotations.NotNull;

public interface WithErrorHandler {

    @NotNull
    ErrorHandler getErrorHandler();
}