package com.sengami.gui_main.component;

import com.sengami.di_base.module.ContextModule;
import com.sengami.di_base.module.ErrorHandlerModule;
import com.sengami.di_base.module.ReactiveSchedulersModule;
import com.sengami.di_main.module.OperationModule;
import com.sengami.di_main.module.PresenterModule;
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