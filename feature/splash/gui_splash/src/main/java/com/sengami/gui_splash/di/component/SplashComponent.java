package com.sengami.gui_splash.di.component;

import com.sengami.gui_base.di.module.ConnectionSourceProviderModule;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ErrorHandlerModule;
import com.sengami.gui_base.di.module.ReactiveSchedulersModule;
import com.sengami.gui_splash.di.module.OperationModule;
import com.sengami.gui_splash.di.module.PresenterModule;
import com.sengami.gui_splash.view.SplashActivity;

import org.jetbrains.annotations.NotNull;

import dagger.Component;

@Component(modules = {
    ConnectionSourceProviderModule.class,
    ContextModule.class,
    ErrorHandlerModule.class,
    OperationModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class
})
public interface SplashComponent {

    void inject(@NotNull final SplashActivity activity);
}