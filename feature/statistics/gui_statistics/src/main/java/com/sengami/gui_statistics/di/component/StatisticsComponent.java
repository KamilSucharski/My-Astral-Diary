package com.sengami.gui_statistics.di.component;

import com.sengami.context.di.module.ContextModule;
import com.sengami.database_connection.di.module.DatabaseConnectionProviderModule;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.gui_statistics.di.module.PresenterModule;
import com.sengami.gui_statistics.view.StatisticsFragment;
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
public interface StatisticsComponent {

    void inject(final StatisticsFragment fragment);
}