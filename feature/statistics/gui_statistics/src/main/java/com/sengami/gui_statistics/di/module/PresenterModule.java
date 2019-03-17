package com.sengami.gui_statistics.di.module;

import com.sengami.domain_statistics.contract.StatisticsContract;
import com.sengami.domain_statistics.presenter.StatisticsPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    StatisticsContract.Presenter diaryEntryListContractPresenter() {
        return new StatisticsPresenter();
    }
}