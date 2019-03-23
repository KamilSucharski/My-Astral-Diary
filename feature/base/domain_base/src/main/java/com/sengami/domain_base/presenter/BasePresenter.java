package com.sengami.domain_base.presenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V> implements ReactivePresenter<V> {

    private final CompositeDisposable disposables = new CompositeDisposable();

    protected abstract List<Disposable> createSubscriptions(@NotNull final V view);

    @Override
    final public void subscribe(@NotNull final V view) {
        disposables.addAll(createSubscriptions(view).toArray(new Disposable[]{}));
    }

    @Override
    final public void unsubscribe() {
        disposables.clear();
    }
}