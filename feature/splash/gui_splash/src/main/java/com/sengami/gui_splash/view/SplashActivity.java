package com.sengami.gui_splash.view;

import android.content.Intent;

import com.sengami.android_operation.di.module.ContextModule;
import com.sengami.android_operation.di.module.WithErrorHandlerModule;
import com.sengami.android_operation.di.module.WithLoadingIndicatorModule;
import com.sengami.android_operation.implementation.EmptyLoadingIndicator;
import com.sengami.android_operation.implementation.ToastErrorHandler;
import com.sengami.domain_base.operation.error.ErrorHandler;
import com.sengami.domain_base.operation.loading.LoadingIndicator;
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
        DaggerSplashComponent.builder()
            .contextModule(new ContextModule(this))
            .withErrorHandlerModule(new WithErrorHandlerModule(this))
            .withLoadingIndicatorModule(new WithLoadingIndicatorModule(this))
            .build()
            .inject(this);
    }

    @Override
    public void navigateToMainView() {
        final FlowCoordinator flowCoordinator = FlowCoordinatorProvider.provide();
        final Intent intent = flowCoordinator.mainActivityIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    @NotNull
    public ErrorHandler getErrorHandler() {
        return new ToastErrorHandler(this);
    }

    @Override
    @NotNull
    public LoadingIndicator getLoadingIndicator() {
        return new EmptyLoadingIndicator();
    }
}