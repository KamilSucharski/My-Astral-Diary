package com.sengami.domain_base.operation.logger;

import org.jetbrains.annotations.NotNull;

public interface Logger {

    void error(@NotNull final Throwable throwable);
}