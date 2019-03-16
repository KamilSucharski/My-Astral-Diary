package com.sengami.domain_splash.contract;

import com.sengami.domain_base.presenter.ReactivePresenter;

public interface SplashContract {

    interface View {

        void navigateToMainView();
    }

    interface Presenter extends ReactivePresenter<View> {
    }
}