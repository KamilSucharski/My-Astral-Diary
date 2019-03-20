package com.sengami.clicks;

import android.view.View;

import org.jetbrains.annotations.NotNull;

import androidx.databinding.ViewDataBinding;

public final class Clicks {

    public static void onClick(@NotNull final ViewDataBinding viewDataBinding,
                               @NotNull final Runnable runnable) {
        onClick(viewDataBinding.getRoot(), runnable);
    }

    public static void onClick(@NotNull final View view,
                               @NotNull final Runnable runnable) {
        view.setOnClickListener(v -> v.post(runnable));
    }
}