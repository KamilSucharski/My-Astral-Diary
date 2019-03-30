package com.sengami.gui_splash.di.component;

import com.sengami.android_operation.di.module.LoggerModule;
import com.sengami.android_operation.di.module.ReactiveSchedulersModule;
import com.sengami.android_operation.di.module.WithErrorHandlerModule;
import com.sengami.android_operation.di.module.WithLoadingIndicatorModule;
import com.sengami.gui_base.database.DatabaseConnectionProviderModule;
import com.sengami.gui_splash.di.module.OperationModule;
import com.sengami.gui_splash.di.module.PresenterModule;
import com.sengami.gui_splash.view.SplashActivity;

import dagger.Component;

@Component(modules = {
    DatabaseConnectionProviderModule.class,
    LoggerModule.class,
    OperationModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class,
    WithErrorHandlerModule.class,
    WithLoadingIndicatorModule.class,
})
public interface SplashComponent {

    void inject(final SplashActivity activity);
}