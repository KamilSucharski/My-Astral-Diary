package com.sengami.domain_statistics.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_statistics.operation.GetDiaryStatisticsOperation;
import com.sengami.domain_statistics.view.StatisticsView;

import org.jetbrains.annotations.NotNull;

public final class StatisticsPresenter extends BasePresenter<StatisticsView> {

    @NotNull
    private final GetDiaryStatisticsOperation getDiaryStatisticsOperation;

    public StatisticsPresenter(@NotNull final GetDiaryStatisticsOperation getDiaryStatisticsOperation) {
        this.getDiaryStatisticsOperation = getDiaryStatisticsOperation;
    }

    @Override
    protected void onSubscribe(@NotNull final StatisticsView view) {
    }
}