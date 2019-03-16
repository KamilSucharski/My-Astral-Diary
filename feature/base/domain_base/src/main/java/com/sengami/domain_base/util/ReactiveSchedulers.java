package com.sengami.domain_base.util;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;

public final class ReactiveSchedulers {

    private final Scheduler subscribeScheduler;
    private final Scheduler observeScheduler;

    public ReactiveSchedulers(@NotNull final Scheduler subscribeScheduler,
                              @NotNull final Scheduler observeScheduler) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
    }

    @NotNull
    public Scheduler getSubscribeScheduler() {
        return subscribeScheduler;
    }

    @NotNull
    public Scheduler getObserveScheduler() {
        return observeScheduler;
    }
}
