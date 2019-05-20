package com.sengami.gui_statistics.di.component;

import com.sengami.android_operation.di.module.OperationConfigurationModule;
import com.sengami.gui_base.database.DatabaseConnectionProviderModule;
import com.sengami.gui_statistics.di.module.MapperModule;
import com.sengami.gui_statistics.di.module.OperationModule;
import com.sengami.gui_statistics.di.module.PresenterModule;
import com.sengami.gui_statistics.view.StatisticsFragment;

import dagger.Component;

@Component(modules = {
    DatabaseConnectionProviderModule.class,
    MapperModule.class,
    OperationModule.class,
    OperationConfigurationModule.class,
    PresenterModule.class,
})
public interface StatisticsComponent {

    void inject(final StatisticsFragment fragment);
}