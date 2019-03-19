package com.sengami.domain_base.util;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;

public interface ReactiveSchedulers {

    @NotNull
    Scheduler getSubscribeScheduler();
    @NotNull
    Scheduler getObserveScheduler();
}
