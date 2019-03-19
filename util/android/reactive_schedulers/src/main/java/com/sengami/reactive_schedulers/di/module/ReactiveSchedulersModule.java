package com.sengami.reactive_schedulers.di.module;

import com.sengami.domain_base.util.ReactiveSchedulers;
import com.sengami.reactive_schedulers.implementation.AndroidReactiveSchedulers;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class ReactiveSchedulersModule {

    @Provides
    @NotNull
    ReactiveSchedulers reactiveSchedulers() {
        return new AndroidReactiveSchedulers();
    }
}