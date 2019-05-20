package com.sengami.android_operation.implementation.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import com.sengami.domain_base.operation.configuration.loading.LoadingIndicator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Provider;

public final class ViewVisibilityLoadingIndicator implements LoadingIndicator {

    @NotNull
    private final Provider<View> viewProvider;
    @Nullable
    private Integer animationDuration;

    public ViewVisibilityLoadingIndicator(@NotNull final Provider<View> viewProvider) {
        this.viewProvider = viewProvider;
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
        final View view = viewProvider.get();
        view.setAlpha(0F);
        view.setVisibility(View.VISIBLE);
        view.animate()
            .alpha(1F)
            .setDuration(getAnimationDuration(view))
            .setListener(null);
    }

    private void hideWithAnimation() {
        final View view = viewProvider.get();
        view.animate()
            .alpha(0F)
            .setDuration(getAnimationDuration(view))
            .setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                }
            });
    }

    private int getAnimationDuration(@NotNull final View view) {
        if (animationDuration == null) {
            animationDuration = view
                .getContext()
                .getResources()
                .getInteger(android.R.integer.config_shortAnimTime);
        }

        return animationDuration;
    }
}