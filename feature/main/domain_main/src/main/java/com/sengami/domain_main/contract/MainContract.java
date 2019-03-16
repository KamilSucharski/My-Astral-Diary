package com.sengami.domain_main.contract;

import com.sengami.domain_base.presenter.ReactivePresenter;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public interface MainContract {

    interface View {
    }

    interface Presenter extends ReactivePresenter<View> {
    }
}