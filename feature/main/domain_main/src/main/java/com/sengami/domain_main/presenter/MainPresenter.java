package com.sengami.domain_main.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_main.contract.MainContract;
import com.sengami.domain_main.operation.GetHelloWorldOperation;

import org.jetbrains.annotations.NotNull;

public final class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    final GetHelloWorldOperation getHelloWorldOperation;

    public MainPresenter(@NotNull final GetHelloWorldOperation getHelloWorldOperation) {
        this.getHelloWorldOperation = getHelloWorldOperation;
    }

    @Override
    protected void onSubscribe(@NotNull final MainContract.View view) {
        disposables.add(
            view.getHelloButtonTrigger()
                .flatMap(x -> getHelloWorldOperation.execute())
                .subscribe(view::showHelloWorld)
        );
    }
}