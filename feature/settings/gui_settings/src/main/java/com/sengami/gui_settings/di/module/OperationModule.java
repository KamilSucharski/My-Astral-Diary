package com.sengami.gui_settings.di.module;

import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_base.util.InternalStoragePathProvider;
import com.sengami.data_settings.operation.local.CreateBackupOperationLocal;
import com.sengami.data_settings.operation.local.ExportToTextFileOperationLocal;
import com.sengami.data_settings.operation.local.RestoreFromBackupOperationLocal;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
import com.sengami.domain_settings.operation.CreateBackupOperation;
import com.sengami.domain_settings.operation.ExportToTextFileOperation;
import com.sengami.domain_settings.operation.RestoreFromBackupOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    CreateBackupOperation createBackupOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                @NotNull final WithErrorHandler withErrorHandler,
                                                @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                @NotNull final InternalStoragePathProvider internalStoragePathProvider) {
        return new CreateBackupOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            databaseConnectionProvider,
            internalStoragePathProvider
        );
    }

    @Provides
    @NotNull
    RestoreFromBackupOperation restoreFromBackupOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                          @NotNull final WithErrorHandler withErrorHandler,
                                                          @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                          @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                          @NotNull final InternalStoragePathProvider internalStoragePathProvider) {
        return new RestoreFromBackupOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            databaseConnectionProvider,
            internalStoragePathProvider
        );
    }

    @Provides
    @NotNull
    ExportToTextFileOperation exportToTextFileOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                        @NotNull final WithErrorHandler withErrorHandler,
                                                        @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                        @NotNull final DatabaseConnectionProvider databaseConnectionProvider) {
        return new ExportToTextFileOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            databaseConnectionProvider
        );
    }
}