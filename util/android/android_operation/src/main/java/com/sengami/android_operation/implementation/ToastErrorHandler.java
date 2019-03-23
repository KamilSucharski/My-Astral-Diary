package com.sengami.android_operation.implementation;

import android.content.Context;
import android.widget.Toast;

import com.sengami.domain_base.operation.error.ErrorHandler;

import org.jetbrains.annotations.NotNull;

public final class ToastErrorHandler implements ErrorHandler {

    @NotNull
    private final Context context;

    public ToastErrorHandler(@NotNull final Context context) {
        this.context = context;
    }

    @Override
    public void handleError(@NotNull final Throwable throwable) {
        Toast.makeText(context, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}