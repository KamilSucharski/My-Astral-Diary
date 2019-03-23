package com.sengami.domain_base.presenter;

import org.jetbrains.annotations.NotNull;

public interface Presenter<V> {

    void subscribe(@NotNull final V view);

    void unsubscribe();
}