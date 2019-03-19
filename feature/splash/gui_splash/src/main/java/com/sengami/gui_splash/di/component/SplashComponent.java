package com.sengami.gui_splash.di.component;

import com.sengami.database_connection.di.module.DatabaseConnectionProviderModule;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_splash.di.module.OperationModule;
import com.sengami.gui_splash.di.module.PresenterModule;
import com.sengami.gui_splash.view.SplashActivity;
import com.sengami.reactive_schedulers.di.module.ReactiveSchedulersModule;
import com.sengami.util_loading_indicator.di.module.WithLoadingIndicatorModule;

import dagger.Component;

@Component(modules = {
    DatabaseConnectionProviderModule.class,
    ContextModule.class,
    WithErrorHandlerModule.class,
    WithLoadingIndicatorModule.class,
    OperationModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class
})
public interface SplashComponent {

    void inject(final SplashActivity activity);
}