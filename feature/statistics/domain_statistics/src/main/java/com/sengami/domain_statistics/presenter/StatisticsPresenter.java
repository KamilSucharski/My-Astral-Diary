package com.sengami.domain_statistics.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_statistics.operation.GetStatisticsOperation;
import com.sengami.domain_statistics.view.StatisticsView;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.Disposable;

public final class StatisticsPresenter extends BasePresenter<StatisticsView> {

    @NotNull
    private final GetStatisticsOperation getStatisticsOperation;

    public StatisticsPresenter(@NotNull final GetStatisticsOperation getStatisticsOperation) {
        this.getStatisticsOperation = getStatisticsOperation;
    }

    @Override
    protected List<Disposable> createSubscriptions(@NotNull final StatisticsView view) {
        return Collections.singletonList(
            subscribeRefreshStatisticsTrigger(view)
        );
    }

    private Disposable subscribeRefreshStatisticsTrigger(@NotNull final StatisticsView view) {
        return view
            .getRefreshStatisticsTrigger()
            .flatMap(x -> getStatisticsOperation.execute())
            .subscribe(view::showStatistics);
    }
}