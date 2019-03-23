package com.sengami.gui_settings.di.component;

import com.sengami.android_operation.di.module.ContextModule;
import com.sengami.android_operation.di.module.LoggerModule;
import com.sengami.android_operation.di.module.ReactiveSchedulersModule;
import com.sengami.android_operation.di.module.WithErrorHandlerModule;
import com.sengami.android_operation.di.module.WithLoadingIndicatorModule;
import com.sengami.database_connection.di.module.DatabaseConnectionProviderModule;
import com.sengami.gui_settings.di.module.MapperModule;
import com.sengami.gui_settings.di.module.OperationModule;
import com.sengami.gui_settings.di.module.PresenterModule;
import com.sengami.gui_settings.view.SettingsFragment;

import dagger.Component;
import pl.apzumi.internal_storage.di.module.StoragePathProviderModule;

@Component(modules = {
    ContextModule.class,
    DatabaseConnectionProviderModule.class,
    LoggerModule.class,
    MapperModule.class,
    OperationModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class,
    StoragePathProviderModule.class,
    WithErrorHandlerModule.class,
    WithLoadingIndicatorModule.class,
})
public interface SettingsComponent {

    void inject(final SettingsFragment fragment);
}