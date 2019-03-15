package com.sengami.di_main.module;

import com.sengami.domain_main.contract.MainContract;
import com.sengami.domain_main.operation.GetHelloWorldOperation;
import com.sengami.domain_main.presenter.MainPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    MainContract.Presenter mainContractPresenter(@NotNull final GetHelloWorldOperation getHelloWorldOperation) {
        return new MainPresenter(getHelloWorldOperation);
    }
}