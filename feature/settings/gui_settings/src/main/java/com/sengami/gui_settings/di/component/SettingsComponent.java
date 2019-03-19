package com.sengami.gui_settings.di.component;

import com.sengami.context.di.module.ContextModule;
import com.sengami.database_connection.di.module.DatabaseConnectionProviderModule;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.gui_settings.di.module.PresenterModule;
import com.sengami.gui_settings.view.SettingsFragment;
import com.sengami.reactive_schedulers.di.module.ReactiveSchedulersModule;
import com.sengami.util_loading_indicator.di.module.WithLoadingIndicatorModule;

import dagger.Component;

@Component(modules = {
    DatabaseConnectionProviderModule.class,
    ContextModule.class,
    WithErrorHandlerModule.class,
    WithLoadingIndicatorModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class
})
public interface SettingsComponent {

    void inject(final SettingsFragment fragment);
}