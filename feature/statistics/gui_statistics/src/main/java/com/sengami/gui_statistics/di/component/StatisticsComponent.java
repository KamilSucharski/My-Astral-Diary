package com.sengami.gui_statistics.di.component;

import com.sengami.gui_base.di.module.ConnectionSourceProviderModule;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ErrorHandlerModule;
import com.sengami.gui_base.di.module.ReactiveSchedulersModule;
import com.sengami.gui_statistics.di.module.PresenterModule;
import com.sengami.gui_statistics.view.StatisticsFragment;

import org.jetbrains.annotations.NotNull;

import dagger.Component;

@Component(modules = {
    ConnectionSourceProviderModule.class,
    ContextModule.class,
    ErrorHandlerModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class
})
public interface StatisticsComponent {

    void inject(@NotNull final StatisticsFragment fragment);
}