package com.sengami.gui_main.di.component;

import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ErrorHandlerModule;
import com.sengami.gui_base.di.module.ReactiveSchedulersModule;
import com.sengami.gui_main.di.module.OperationModule;
import com.sengami.gui_main.di.module.PresenterModule;
import com.sengami.gui_main.view.MainActivity;

import org.jetbrains.annotations.NotNull;

import dagger.Component;

@Component(modules = {
        ContextModule.class,
        ErrorHandlerModule.class,
        OperationModule.class,
        PresenterModule.class,
        ReactiveSchedulersModule.class
})
public interface MainComponent {

    void inject(@NotNull final MainActivity activity);
}