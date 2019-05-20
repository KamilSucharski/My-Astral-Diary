package com.sengami.gui_splash.di.component;

import com.sengami.android_operation.di.module.OperationConfigurationModule;
import com.sengami.gui_base.database.DatabaseConnectionProviderModule;
import com.sengami.gui_splash.di.module.OperationModule;
import com.sengami.gui_splash.di.module.PresenterModule;
import com.sengami.gui_splash.view.SplashActivity;

import dagger.Component;

@Component(modules = {
    DatabaseConnectionProviderModule.class,
    OperationModule.class,
    OperationConfigurationModule.class,
    PresenterModule.class,
})
public interface SplashComponent {

    void inject(final SplashActivity activity);
}