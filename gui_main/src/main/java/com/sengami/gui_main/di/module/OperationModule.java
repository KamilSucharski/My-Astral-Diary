package com.sengami.gui_main.di.module;

import com.sengami.data_main.operation.local.GetHelloWorldOperationLocal;
import com.sengami.domain_base.util.ErrorHandler;
import com.sengami.domain_base.util.ReactiveSchedulers;
import com.sengami.domain_main.operation.GetHelloWorldOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    GetHelloWorldOperation getHelloWorldOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                  @NotNull final ErrorHandler errorHandler) {
        return new GetHelloWorldOperationLocal(reactiveSchedulers, errorHandler);
    }
}