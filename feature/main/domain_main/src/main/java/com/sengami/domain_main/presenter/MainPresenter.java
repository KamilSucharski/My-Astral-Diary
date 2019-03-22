package com.sengami.domain_main.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_main.view.MainView;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.Disposable;

public final class MainPresenter extends BasePresenter<MainView> {

    @Override
    protected List<Disposable> createSubscriptions(@NotNull final MainView view) {
        return Collections.emptyList();
    }
}