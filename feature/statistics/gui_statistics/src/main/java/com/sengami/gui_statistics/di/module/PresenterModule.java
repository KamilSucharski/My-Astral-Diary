package com.sengami.gui_statistics.di.module;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_statistics.operation.GetStatisticsOperation;
import com.sengami.domain_statistics.presenter.DayProgressPresenter;
import com.sengami.domain_statistics.presenter.StatisticsPresenter;
import com.sengami.domain_statistics.presenter.YearProgressPresenter;
import com.sengami.domain_statistics.view.DayProgressView;
import com.sengami.domain_statistics.view.StatisticsView;
import com.sengami.domain_statistics.view.YearProgressView;

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

    @Provides
    @NotNull
    Presenter<DayProgressView> dayProgressViewPresenter() {
        return new DayProgressPresenter();
    }

    @Provides
    @NotNull
    Presenter<YearProgressView> yearProgressViewPresenter() {
        return new YearProgressPresenter();
    }
}