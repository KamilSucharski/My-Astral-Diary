package com.sengami.gui_statistics.di.component;

import com.sengami.gui_base.di.module.ConnectionSourceProviderModule;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ReactiveSchedulersModule;
import com.sengami.gui_base.di.module.WithErrorHandlerModule;
import com.sengami.gui_base.di.module.WithLoadingIndicatorModule;
import com.sengami.gui_statistics.di.module.PresenterModule;
import com.sengami.gui_statistics.view.StatisticsFragment;

import dagger.Component;

@Component(modules = {
    ConnectionSourceProviderModule.class,
    ContextModule.class,
    WithErrorHandlerModule.class,
    WithLoadingIndicatorModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class
})
public interface StatisticsComponent {

    void inject(final StatisticsFragment fragment);
}