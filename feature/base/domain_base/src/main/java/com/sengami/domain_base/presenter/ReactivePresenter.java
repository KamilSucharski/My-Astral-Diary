package com.sengami.domain_base.presenter;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;

public interface ReactivePresenter<V> {

    CompositeDisposable disposables = new CompositeDisposable();

    void subscribe(@NotNull final V view);

    void unsubscribe();
}
