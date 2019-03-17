package com.sengami.domain_statistics.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_statistics.contract.StatisticsContract;

import org.jetbrains.annotations.NotNull;

public final class StatisticsPresenter extends BasePresenter<StatisticsContract.View> implements StatisticsContract.Presenter {

    @Override
    protected void onSubscribe(@NotNull final StatisticsContract.View view) {
    }
}