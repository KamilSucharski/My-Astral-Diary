package com.sengami.android_operation.implementation.logger;

import android.util.Log;

import com.sengami.domain_base.operation.configuration.logger.Logger;

import org.jetbrains.annotations.NotNull;

public final class AndroidLogger implements Logger {

    @Override
    public void error(@NotNull final Throwable throwable) {
        Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    }
}