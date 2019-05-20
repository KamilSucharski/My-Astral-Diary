package com.sengami.android_operation.implementation;

import com.sengami.android_operation.implementation.error.EmptyErrorHandler;
import com.sengami.android_operation.implementation.loading.EmptyLoadingIndicator;
import com.sengami.android_operation.implementation.logger.AndroidLogger;
import com.sengami.android_operation.implementation.schedulers.AndroidReactiveSchedulers;
import com.sengami.domain_base.operation.configuration.OperationConfiguration;
import com.sengami.domain_base.operation.configuration.error.ErrorHandler;
import com.sengami.domain_base.operation.configuration.loading.LoadingIndicator;
import com.sengami.domain_base.operation.configuration.logger.Logger;
import com.sengami.domain_base.operation.configuration.schedulers.ReactiveSchedulers;

import org.jetbrains.annotations.NotNull;

public final class AndroidOperationConfiguration implements OperationConfiguration {

    @NotNull
    private ReactiveSchedulers reactiveSchedulers = new AndroidReactiveSchedulers();
    @NotNull
    private Logger logger = new AndroidLogger();
    @NotNull
    private ErrorHandler errorHandler = new EmptyErrorHandler();
    @NotNull
    private LoadingIndicator loadingIndicator = new EmptyLoadingIndicator();

    private AndroidOperationConfiguration() {
    }

    public static AndroidOperationConfiguration create() {
        return new AndroidOperationConfiguration();
    }

    @NotNull
    public AndroidOperationConfiguration withReactiveSchedulers(@NotNull final ReactiveSchedulers reactiveSchedulers) {
        this.reactiveSchedulers = reactiveSchedulers;
        return this;
    }

    @NotNull
    public AndroidOperationConfiguration withLogger(@NotNull final Logger logger) {
        this.logger = logger;
        return this;
    }

    @NotNull
    public AndroidOperationConfiguration withErrorHandler(@NotNull final ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    @NotNull
    public AndroidOperationConfiguration withLoadingIndicator(@NotNull final LoadingIndicator loadingIndicator) {
        this.loadingIndicator = loadingIndicator;
        return this;
    }

    @Override
    @NotNull
    public ReactiveSchedulers getReactiveSchedulers() {
        return reactiveSchedulers;
    }

    @Override
    @NotNull
    public Logger getLogger() {
        return logger;
    }

    @Override
    @NotNull
    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    @Override
    @NotNull
    public LoadingIndicator getLoadingIndicator() {
        return loadingIndicator;
    }
}