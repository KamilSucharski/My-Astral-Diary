package com.sengami.gui_settings.di.component;

import com.sengami.database_connection.di.module.DatabaseConnectionProviderModule;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ReactiveSchedulersModule;
import com.sengami.gui_settings.di.module.PresenterModule;
import com.sengami.gui_settings.view.SettingsFragment;
import com.sengami.util_loading_indicator.di.module.WithLoadingIndicatorModule;

import org.jetbrains.annotations.NotNull;

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

    void inject(@NotNull final SettingsFragment fragment);
}