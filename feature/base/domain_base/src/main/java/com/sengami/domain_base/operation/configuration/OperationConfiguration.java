package com.sengami.domain_base.operation.configuration;

import com.sengami.domain_base.operation.configuration.error.ErrorHandler;
import com.sengami.domain_base.operation.configuration.loading.LoadingIndicator;
import com.sengami.domain_base.operation.configuration.logger.Logger;
import com.sengami.domain_base.operation.configuration.schedulers.ReactiveSchedulers;

import org.jetbrains.annotations.NotNull;

public interface OperationConfiguration {

    @NotNull
    ReactiveSchedulers getReactiveSchedulers();

    @NotNull
    Logger getLogger();

    @NotNull
    ErrorHandler getErrorHandler();

    @NotNull
    LoadingIndicator getLoadingIndicator();
}