package com.sengami.android_operation.di.module;

import com.sengami.android_operation.implementation.AndroidLogger;
import com.sengami.domain_base.operation.logger.Logger;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class LoggerModule {

    @Provides
    @NotNull
    Logger logger() {
        return new AndroidLogger();
    }
}