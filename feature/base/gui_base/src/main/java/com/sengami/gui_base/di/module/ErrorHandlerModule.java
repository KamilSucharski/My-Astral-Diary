package com.sengami.gui_base.di.module;

import android.content.Context;

import com.sengami.domain_base.util.ErrorHandler;
import com.sengami.gui_base.error.ToastErrorHandler;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class ErrorHandlerModule {

    @Provides
    @NotNull
    ErrorHandler errorHandler(@NotNull final Context context) {
        return new ToastErrorHandler(context);
    }
}