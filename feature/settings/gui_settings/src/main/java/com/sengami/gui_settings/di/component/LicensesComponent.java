package com.sengami.gui_settings.di.component;

import com.sengami.gui_settings.di.module.PresenterModule;
import com.sengami.gui_settings.view.LicensesActivity;

import dagger.Component;

@Component(modules = {
    PresenterModule.class
})
public interface LicensesComponent {

    void inject(final LicensesActivity activity);
}