package com.sengami.domain_main.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_main.contract.MainContract;

import org.jetbrains.annotations.NotNull;

public final class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    protected void onSubscribe(@NotNull final MainContract.View view) {
    }
}