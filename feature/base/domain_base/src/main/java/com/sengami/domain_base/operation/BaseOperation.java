package com.sengami.domain_base.operation;

import com.sengami.domain_base.operation.configuration.OperationConfiguration;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public abstract class BaseOperation<T> implements Operation<T> {

    protected abstract Observable<T> getObservable();

    @NotNull
    private final OperationConfiguration operationConfiguration;

    protected BaseOperation(@NotNull final OperationConfiguration operationConfiguration) {
        this.operationConfiguration = operationConfiguration;
    }

    @Override
    public Observable<T> execute() {
        showLoadingIndicator();
        return getObservable()
            .subscribeOn(operationConfiguration.getReactiveSchedulers().getSubscribeScheduler())
            .observeOn(operationConfiguration.getReactiveSchedulers().getObserveScheduler())
            .onErrorResumeNext(this::handleError)
            .map(result -> {
                hideLoadingIndicator();
                return result;
            })
            .subscribeOn(operationConfiguration.getReactiveSchedulers().getObserveScheduler());
    }

    private Observable<T> handleError(@NotNull final Throwable throwable) {
        return Observable.just(false)
            .map(x -> {
                operationConfiguration
                    .getLogger()
                    .error(throwable);
                hideLoadingIndicator();
                operationConfiguration
                    .getErrorHandler()
                    .handleError(throwable);
                return x;
            })
            .flatMap(x -> Observable.empty());
    }

    private void showLoadingIndicator() {
        operationConfiguration
            .getLoadingIndicator()
            .setLoading(true);
    }

    private void hideLoadingIndicator() {
        operationConfiguration
            .getLoadingIndicator()
            .setLoading(false);
    }
}