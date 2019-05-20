package com.sengami.gui_splash.view;

import android.animation.Animator;
import android.content.Intent;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.sengami.android_operation.di.module.OperationConfigurationModule;
import com.sengami.android_operation.implementation.AndroidOperationConfiguration;
import com.sengami.android_operation.implementation.error.ToastErrorHandler;
import com.sengami.domain_base.operation.configuration.OperationConfiguration;
import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_splash.view.SplashView;
import com.sengami.gui_base.navigation.FlowCoordinator;
import com.sengami.gui_base.navigation.FlowCoordinatorProvider;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_splash.R;
import com.sengami.gui_splash.databinding.ActivitySplashBinding;
import com.sengami.gui_splash.di.component.DaggerSplashComponent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.codetail.animation.ViewAnimationUtils;

public final class SplashActivity
    extends BaseActivity<Presenter<SplashView>, ActivitySplashBinding>
    implements SplashView {

    @Inject
    @Override
    protected void injectPresenter(@NotNull final Presenter<SplashView> presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void inject() {
        final OperationConfiguration operationConfiguration = AndroidOperationConfiguration
            .create()
            .withErrorHandler(new ToastErrorHandler(this));

        DaggerSplashComponent
            .builder()
            .operationConfigurationModule(new OperationConfigurationModule(operationConfiguration))
            .build()
            .inject(this);
    }

    @Override
    protected void init() {
        super.init();
        binding.layout.post(this::revealScreen);
    }

    @Override
    public void navigateToMainView() {
        final FlowCoordinator flowCoordinator = FlowCoordinatorProvider.provide();
        final Intent intent = flowCoordinator.mainActivityIntent(this);
        startActivity(intent);
        finish();
    }

    private void revealScreen() {
        final int animationCenterX = binding.layout.getRight();
        final int animationCenterY = binding.layout.getBottom();
        final float startRadius = 0f;
        final float endRadius = (float) Math.hypot(animationCenterX, animationCenterY);
        final Animator animator = ViewAnimationUtils.createCircularReveal(
            binding.layout,
            animationCenterX,
            animationCenterY,
            startRadius,
            endRadius
        );
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(750);
        animator.start();
    }
}