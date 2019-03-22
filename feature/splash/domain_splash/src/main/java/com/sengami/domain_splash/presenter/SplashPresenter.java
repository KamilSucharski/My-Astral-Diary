package com.sengami.domain_splash.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_splash.operation.PrepareDataOperation;
import com.sengami.domain_splash.view.SplashView;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public final class SplashPresenter extends BasePresenter<SplashView> {

    @NotNull
    private final PrepareDataOperation prepareDataOperation;

    public SplashPresenter(@NotNull final PrepareDataOperation prepareDataOperation) {
        this.prepareDataOperation = prepareDataOperation;
    }

    @Override
    protected List<Disposable> createSubscriptions(@NotNull final SplashView view) {
        return Arrays.asList(
            subscribePrepareDataOperation(view)
        );
    }

    private Disposable subscribePrepareDataOperation(@NotNull final SplashView view) {
            return prepareDataOperation
                .execute()
                .subscribe(result -> view.navigateToMainView());
    }
}