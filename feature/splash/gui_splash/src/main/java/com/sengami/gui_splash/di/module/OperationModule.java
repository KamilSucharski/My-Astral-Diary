package com.sengami.gui_splash.di.module;

import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_splash.operation.local.PrepareDataOperationLocal;
import com.sengami.domain_base.operation.configuration.OperationConfiguration;
import com.sengami.domain_splash.operation.PrepareDataOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    PrepareDataOperation prepareDataOperation(@NotNull final OperationConfiguration operationConfiguration,
                                              @NotNull final DatabaseConnectionProvider databaseConnectionProvider) {
        return new PrepareDataOperationLocal(
            operationConfiguration,
            databaseConnectionProvider
        );
    }
}