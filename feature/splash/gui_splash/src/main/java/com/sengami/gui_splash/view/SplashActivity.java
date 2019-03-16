package com.sengami.gui_splash.view;

import android.widget.Toast;

import com.sengami.domain_splash.contract.SplashContract;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ErrorHandlerModule;
import com.sengami.gui_base.error.ToastErrorHandler;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_main.view.MainActivity;
import com.sengami.gui_splash.R;
import com.sengami.gui_splash.databinding.ActivitySplashBinding;
import com.sengami.gui_splash.di.component.DaggerSplashComponent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<SplashContract.Presenter, ActivitySplashBinding> implements SplashContract.View {

    @Inject
    @Override
    protected void injectPresenter(@NotNull final SplashContract.Presenter presenter) {
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
            .build()
            .inject(this);
    }

    @Override
    public void navigateToMainView() {
        changeActivity(MainActivity.class);
    }
}