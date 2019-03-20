package com.sengami.gui_splash.view;

import com.sengami.context.di.module.ContextModule;
import com.sengami.domain_base.error.ErrorHandler;
import com.sengami.domain_base.loading.LoadingIndicator;
import com.sengami.domain_splash.presenter.SplashPresenter;
import com.sengami.domain_splash.view.SplashView;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.error_handler.implementation.ToastErrorHandler;
import com.sengami.gui_base.BaseActivity;
import com.sengami.gui_main.view.MainActivity;
import com.sengami.gui_splash.R;
import com.sengami.gui_splash.databinding.ActivitySplashBinding;
import com.sengami.gui_splash.di.component.DaggerSplashComponent;
import com.sengami.util_loading_indicator.di.module.WithLoadingIndicatorModule;
import com.sengami.util_loading_indicator.implementation.EmptyLoadingIndicator;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class SplashActivity
    extends BaseActivity<SplashPresenter, ActivitySplashBinding>
    implements SplashView {

    @Inject
    @Override
    protected void injectPresenter(@NotNull final SplashPresenter presenter) {
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
        changeActivity(MainActivity.class);
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