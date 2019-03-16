package com.sengami.domain_main.contract;

import com.sengami.domain_base.presenter.ReactivePresenter;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public interface MainContract {

    interface View {

        @NotNull
        Observable<Boolean> getHelloButtonTrigger();

        void showHelloWorld(String text);
    }

    interface Presenter extends ReactivePresenter<View> {}
}