package com.sengami.gui_splash.di.module;

import com.sengami.domain_splash.operation.PrepareDataOperation;
import com.sengami.domain_splash.presenter.SplashPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    SplashPresenter mainContractPresenter(@NotNull final PrepareDataOperation prepareDataOperation) {
        return new SplashPresenter(prepareDataOperation);
    }
}