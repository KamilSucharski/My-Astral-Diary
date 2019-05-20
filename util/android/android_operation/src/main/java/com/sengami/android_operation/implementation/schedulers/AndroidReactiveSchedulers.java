package com.sengami.android_operation.implementation.schedulers;

import com.sengami.domain_base.operation.configuration.schedulers.ReactiveSchedulers;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class AndroidReactiveSchedulers implements ReactiveSchedulers {

    private final Scheduler subscribeScheduler = Schedulers.io();
    private final Scheduler observeScheduler = AndroidSchedulers.mainThread();

    @Override
    @NotNull
    public Scheduler getSubscribeScheduler() {
        return subscribeScheduler;
    }

    @Override
    @NotNull
    public Scheduler getObserveScheduler() {
        return observeScheduler;
    }
}