package com.sengami.domain_splash.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_splash.operation.PrepareDataOperation;
import com.sengami.domain_splash.view.SplashView;

import org.jetbrains.annotations.NotNull;

public final class SplashPresenter extends BasePresenter<SplashView> {

    @NotNull
    private final PrepareDataOperation prepareDataOperation;

    public SplashPresenter(@NotNull final PrepareDataOperation prepareDataOperation) {
        this.prepareDataOperation = prepareDataOperation;
    }

    @Override
    protected void onSubscribe(@NotNull final SplashView view) {
        subscribePrepareDataOperation(view);
    }

    private void subscribePrepareDataOperation(@NotNull final SplashView view) {
        disposables.add(
            prepareDataOperation
                .execute()
                .subscribe(result -> view.navigateToMainView())
        );
    }
}