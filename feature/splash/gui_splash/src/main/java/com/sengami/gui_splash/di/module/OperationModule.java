package com.sengami.gui_splash.di.module;

import com.sengami.data_base.util.ConnectionSourceProvider;
import com.sengami.data_splash.operation.local.PrepareDataOperationLocal;
import com.sengami.domain_base.util.ReactiveSchedulers;
import com.sengami.domain_base.util.error.WithErrorHandler;
import com.sengami.domain_base.util.loading.WithLoadingIndicator;
import com.sengami.domain_splash.operation.PrepareDataOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    PrepareDataOperation prepareDataOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                              @NotNull final WithErrorHandler withErrorHandler,
                                              @NotNull final WithLoadingIndicator withLoadingIndicator,
                                              @NotNull final ConnectionSourceProvider connectionSourceProvider) {
        return new PrepareDataOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            connectionSourceProvider
        );
    }
}