package com.sengami.domain_statistics.view;

import com.sengami.domain_base.model.Statistics;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public interface StatisticsView extends WithErrorHandler, WithLoadingIndicator {

    @NotNull
    Observable<Boolean> getRefreshStatisticsTrigger();

    void showStatistics(@NotNull final Statistics statistics);
}