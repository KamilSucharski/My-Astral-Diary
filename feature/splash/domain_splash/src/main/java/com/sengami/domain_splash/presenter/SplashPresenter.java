package com.sengami.domain_splash.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_splash.contract.SplashContract;
import com.sengami.domain_splash.operation.PrepareDataOperation;

import org.jetbrains.annotations.NotNull;

public final class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    @NotNull
    final PrepareDataOperation prepareDataOperation;

    public SplashPresenter(@NotNull final PrepareDataOperation prepareDataOperation) {
        this.prepareDataOperation = prepareDataOperation;
    }

    @Override
    protected void onSubscribe(@NotNull final SplashContract.View view) {
        subscribePrepareDataOperation(view);
    }

    private void subscribePrepareDataOperation(@NotNull final SplashContract.View view) {
        disposables.add(
            prepareDataOperation
                .execute()
                .subscribe(result -> view.navigateToMainView())
        );
    }
}