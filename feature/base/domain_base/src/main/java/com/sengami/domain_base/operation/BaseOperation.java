package com.sengami.domain_base.operation;

import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.logger.Logger;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public abstract class BaseOperation<T> implements Operation<T> {

    protected abstract Observable<T> getObservable();

    private final ReactiveSchedulers reactiveSchedulers;
    private final WithErrorHandler withErrorHandler;
    private final WithLoadingIndicator withLoadingIndicator;
    private final Logger logger;

    protected BaseOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                            @NotNull final WithErrorHandler withErrorHandler,
                            @NotNull final WithLoadingIndicator withLoadingIndicator,
                            @NotNull final Logger logger) {
        this.reactiveSchedulers = reactiveSchedulers;
        this.withErrorHandler = withErrorHandler;
        this.withLoadingIndicator = withLoadingIndicator;
        this.logger = logger;
    }

    @Override
    public Observable<T> execute() {
        showLoadingIndicator();
        return getObservable()
            .subscribeOn(reactiveSchedulers.getSubscribeScheduler())
            .observeOn(reactiveSchedulers.getObserveScheduler())
            .onErrorResumeNext(this::handleError)
            .map(result -> {
                hideLoadingIndicator();
                return result;
            })
            .subscribeOn(reactiveSchedulers.getObserveScheduler());
    }

    private Observable<T> handleError(@NotNull final Throwable throwable) {
        return Observable.just(false)
            .map(x -> {
                logger.error(throwable);
                hideLoadingIndicator();
                withErrorHandler.getErrorHandler().handleError(throwable);
                return x;
            })
            .flatMap(x -> Observable.empty());
    }

    private void showLoadingIndicator() {
        withLoadingIndicator.getLoadingIndicator().setLoading(true);
    }

    private void hideLoadingIndicator() {
        withLoadingIndicator.getLoadingIndicator().setLoading(false);
    }
}