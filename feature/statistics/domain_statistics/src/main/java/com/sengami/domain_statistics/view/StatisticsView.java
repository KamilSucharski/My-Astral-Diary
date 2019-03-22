package com.sengami.domain_statistics.view;

import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.model.Statistics;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public interface StatisticsView extends WithErrorHandler, WithLoadingIndicator {

    @NotNull
    Observable<Boolean> getRefreshStatisticsTrigger();

    void showStatistics(@NotNull final Statistics statistics);
}