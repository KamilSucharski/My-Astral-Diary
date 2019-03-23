package com.sengami.gui_statistics.di.module;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_statistics.operation.GetStatisticsOperation;
import com.sengami.domain_statistics.presenter.StatisticsPresenter;
import com.sengami.domain_statistics.view.StatisticsView;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    Presenter<StatisticsView> statisticsPresenter(@NotNull final GetStatisticsOperation getStatisticsOperation) {
        return new StatisticsPresenter(getStatisticsOperation);
    }
}