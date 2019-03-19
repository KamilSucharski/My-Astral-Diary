package com.sengami.gui_splash.di.module;

import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_splash.operation.local.PrepareDataOperationLocal;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
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
                                              @NotNull final DatabaseConnectionProvider databaseConnectionProvider) {
        return new PrepareDataOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            databaseConnectionProvider
        );
    }
}