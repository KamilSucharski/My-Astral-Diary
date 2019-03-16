package com.sengami.gui_base.di.module;

import com.sengami.domain_base.util.ReactiveSchedulers;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public final class ReactiveSchedulersModule {

    @Provides
    @NotNull
    ReactiveSchedulers reactiveSchedulers() {
        return new ReactiveSchedulers(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}