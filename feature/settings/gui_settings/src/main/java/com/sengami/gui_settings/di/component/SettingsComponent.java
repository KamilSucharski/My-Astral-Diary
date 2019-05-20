package com.sengami.gui_settings.di.component;

import com.sengami.android_database.di.module.DatabaseFileProviderModule;
import com.sengami.android_operation.di.module.OperationConfigurationModule;
import com.sengami.gui_base.database.DatabaseConnectionProviderModule;
import com.sengami.gui_settings.di.module.MapperModule;
import com.sengami.gui_settings.di.module.OperationModule;
import com.sengami.gui_settings.di.module.PresenterModule;
import com.sengami.gui_settings.view.SettingsFragment;

import dagger.Component;
@Component(modules = {
    DatabaseConnectionProviderModule.class,
    MapperModule.class,
    OperationModule.class,
    OperationConfigurationModule.class,
    PresenterModule.class,
    DatabaseFileProviderModule.class,
})
public interface SettingsComponent {

    void inject(final SettingsFragment fragment);
}