package com.sengami.gui_main.di.component;

import com.sengami.gui_main.di.module.PresenterModule;
import com.sengami.gui_main.view.MainActivity;

import dagger.Component;

@Component(modules = {
    PresenterModule.class,
})
public interface MainComponent {

    void inject(final MainActivity activity);
}