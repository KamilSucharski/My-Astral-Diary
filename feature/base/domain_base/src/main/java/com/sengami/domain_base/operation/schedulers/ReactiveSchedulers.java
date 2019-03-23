package com.sengami.domain_base.operation.schedulers;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;

public interface ReactiveSchedulers {

    @NotNull
    Scheduler getSubscribeScheduler();
    @NotNull
    Scheduler getObserveScheduler();
}