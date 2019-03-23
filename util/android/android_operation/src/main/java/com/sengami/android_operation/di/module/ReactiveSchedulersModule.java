package com.sengami.android_operation.di.module;

import com.sengami.android_operation.implementation.AndroidReactiveSchedulers;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;

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