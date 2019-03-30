package com.sengami.gui_statistics.di.component;

import com.sengami.android_operation.di.module.LoggerModule;
import com.sengami.android_operation.di.module.ReactiveSchedulersModule;
import com.sengami.android_operation.di.module.WithErrorHandlerModule;
import com.sengami.android_operation.di.module.WithLoadingIndicatorModule;
import com.sengami.gui_base.database.DatabaseConnectionProviderModule;
import com.sengami.gui_statistics.di.module.MapperModule;
import com.sengami.gui_statistics.di.module.OperationModule;
import com.sengami.gui_statistics.di.module.PresenterModule;
import com.sengami.gui_statistics.view.StatisticsFragment;

import dagger.Component;

@Component(modules = {
    DatabaseConnectionProviderModule.class,
    LoggerModule.class,
    MapperModule.class,
    OperationModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class,
    WithErrorHandlerModule.class,
    WithLoadingIndicatorModule.class,
})
public interface StatisticsComponent {

    void inject(final StatisticsFragment fragment);
}