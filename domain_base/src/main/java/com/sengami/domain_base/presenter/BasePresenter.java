package com.sengami.domain_base.presenter;

import org.jetbrains.annotations.NotNull;


public abstract class BasePresenter<V> implements ReactivePresenter<V> {

    protected V view;
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