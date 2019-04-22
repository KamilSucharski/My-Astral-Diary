package com.sengami.domain_statistics.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_statistics.view.DayProgressView;
import com.sengami.domain_statistics.view.YearProgressView;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.Disposable;

public final class YearProgressPresenter extends BasePresenter<YearProgressView> {

    @Override
    protected List<Disposable> createSubscriptions(@NotNull final YearProgressView view) {
        return Collections.emptyList();
    }
}