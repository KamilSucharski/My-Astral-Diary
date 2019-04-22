package com.sengami.gui_statistics.di.component;

import com.sengami.gui_statistics.di.module.PresenterModule;
import com.sengami.gui_statistics.view.progress.DayProgress;
import com.sengami.gui_statistics.view.progress.YearProgress;

import dagger.Component;

@Component(modules = {
    PresenterModule.class
})
public interface YearProgressComponent {

    void inject(final YearProgress view);
}