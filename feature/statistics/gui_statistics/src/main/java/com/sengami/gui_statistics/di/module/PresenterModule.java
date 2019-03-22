package com.sengami.gui_statistics.di.module;

import com.sengami.domain_statistics.operation.GetDiaryStatisticsOperation;
import com.sengami.domain_statistics.presenter.StatisticsPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    StatisticsPresenter statisticsPresenter(@NotNull final GetDiaryStatisticsOperation getDiaryStatisticsOperation) {
        return new StatisticsPresenter(getDiaryStatisticsOperation);
    }
}