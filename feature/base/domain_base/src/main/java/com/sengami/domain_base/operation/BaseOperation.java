package com.sengami.domain_base.operation;

import com.sengami.domain_base.util.ErrorHandler;
import com.sengami.domain_base.util.ReactiveSchedulers;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public abstract class BaseOperation<T> implements Operation<T> {

    protected abstract Observable<T> getObservable();

    private final ReactiveSchedulers reactiveSchedulers;
    private final ErrorHandler errorHandler;

    public BaseOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                         @NotNull final ErrorHandler errorHandler) {
        this.reactiveSchedulers = reactiveSchedulers;
        this.errorHandler = errorHandler;
    }

    @Override
    public Observable<T> execute() {
        return getObservable()
            .onErrorResumeNext(this::handleError)
            .observeOn(reactiveSchedulers.getObserveScheduler())
            .subscribeOn(reactiveSchedulers.getSubscribeScheduler());
    }

    private Observable<T> handleError(@NotNull final Throwable throwable) {
        errorHandler.handleError(throwable);
        return Observable.empty();
    }
}
