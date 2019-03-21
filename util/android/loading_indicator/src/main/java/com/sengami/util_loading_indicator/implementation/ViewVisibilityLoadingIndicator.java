package com.sengami.util_loading_indicator.implementation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import com.sengami.domain_base.loading.LoadingIndicator;

import org.jetbrains.annotations.NotNull;

public final class ViewVisibilityLoadingIndicator implements LoadingIndicator {

    @NotNull
    private final View view;
    private final int animationDuration;

    public ViewVisibilityLoadingIndicator(@NotNull final View view) {
        this.view = view;
        this.animationDuration = view
            .getContext()
            .getResources()
            .getInteger(android.R.integer.config_shortAnimTime);
    }

    @Override
    public void setLoading(final boolean loading) {
        if (loading) {
            showWithAnimation();
        } else {
            hideWithAnimation();
        }
    }

    private void showWithAnimation() {
        view.setAlpha(0F);
        view.setVisibility(View.VISIBLE);
        view.animate()
            .alpha(1F)
            .setDuration(animationDuration)
            .setListener(null);
    }

    private void hideWithAnimation() {
        view.animate()
            .alpha(0F)
            .setDuration(animationDuration)
            .setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                }
            });
    }
}