package com.sengami.gui_main.di.module;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_main.presenter.MainPresenter;
import com.sengami.domain_main.view.MainView;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    Presenter<MainView> mainPresenter() {
        return new MainPresenter();
    }
}