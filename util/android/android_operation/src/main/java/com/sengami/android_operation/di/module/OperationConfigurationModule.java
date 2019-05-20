package com.sengami.android_operation.di.module;

import com.sengami.domain_base.operation.configuration.OperationConfiguration;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationConfigurationModule {

    @NotNull
    private final OperationConfiguration operationConfiguration;

    public OperationConfigurationModule(@NotNull final OperationConfiguration operationConfiguration) {
        this.operationConfiguration = operationConfiguration;
    }

    @Provides
    @NotNull
    OperationConfiguration operationConfiguration() {
        return operationConfiguration;
    }
}