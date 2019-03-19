package com.sengami.util_loading_indicator;

import android.view.View;

import com.sengami.domain_base.util.loading.LoadingIndicator;

import org.jetbrains.annotations.NotNull;

public final class ViewVisibilityLoadingIndicator implements LoadingIndicator {

    @NotNull
    private final View view;

    public ViewVisibilityLoadingIndicator(@NotNull final View view) {
        this.view = view;
    }

    @Override
    public void setLoading(final boolean loading) {
        view.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}