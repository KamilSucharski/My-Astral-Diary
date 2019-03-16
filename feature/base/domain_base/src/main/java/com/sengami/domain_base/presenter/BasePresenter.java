package com.sengami.domain_base.presenter;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V> implements ReactivePresenter<V> {

    protected V view;
    protected CompositeDisposable disposables = new CompositeDisposable();

    protected abstract void onSubscribe(@NotNull final V view);

    @Override
    final public void subscribe(@NotNull final V view) {
        this.view = view;
        onSubscribe(view);
    }

    @Override
    final public void unsubscribe() {
        disposables.clear();
        view = null;
    }
}