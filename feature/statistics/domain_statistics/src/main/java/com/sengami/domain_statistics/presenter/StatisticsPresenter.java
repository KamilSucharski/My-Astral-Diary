package com.sengami.domain_statistics.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_statistics.operation.GetDiaryStatisticsOperation;
import com.sengami.domain_statistics.view.StatisticsView;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public final class StatisticsPresenter extends BasePresenter<StatisticsView> {

    @NotNull
    private final GetDiaryStatisticsOperation getDiaryStatisticsOperation;

    public StatisticsPresenter(@NotNull final GetDiaryStatisticsOperation getDiaryStatisticsOperation) {
        this.getDiaryStatisticsOperation = getDiaryStatisticsOperation;
    }

    @Override
    protected List<Disposable> createSubscriptions(@NotNull final StatisticsView view) {
        return Arrays.asList(
            subscribeRefreshStatisticsTrigger(view)
        );
    }

    private Disposable subscribeRefreshStatisticsTrigger(@NotNull final StatisticsView view) {
        return view
            .getRefreshStatisticsTrigger()
            .flatMap(x -> getDiaryStatisticsOperation.execute())
            .subscribe(view::showStatistics);
    }
}