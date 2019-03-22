package com.sengami.gui_settings.di.module;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_base.util.ExternalStoragePathProvider;
import com.sengami.data_base.util.InternalStoragePathProvider;
import com.sengami.data_settings.operation.local.CreateBackupOperationLocal;
import com.sengami.data_settings.operation.local.ExportToTextFileOperationLocal;
import com.sengami.data_settings.operation.local.RestoreFromBackupOperationLocal;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.model.DiaryEntry;
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
                                                @NotNull final InternalStoragePathProvider internalStoragePathProvider,
                                                @NotNull final ExternalStoragePathProvider externalStoragePathProvider) {
        return new CreateBackupOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            internalStoragePathProvider,
            externalStoragePathProvider
        );
    }

    @Provides
    @NotNull
    RestoreFromBackupOperation restoreFromBackupOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                          @NotNull final WithErrorHandler withErrorHandler,
                                                          @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                          @NotNull final InternalStoragePathProvider internalStoragePathProvider) {
        return new RestoreFromBackupOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            internalStoragePathProvider
        );
    }

    @Provides
    @NotNull
    ExportToTextFileOperation exportToTextFileOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                        @NotNull final WithErrorHandler withErrorHandler,
                                                        @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                        @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                        @NotNull final ExternalStoragePathProvider externalStoragePathProvider,
                                                        @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> diaryEntryMapper) {
        return new ExportToTextFileOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            databaseConnectionProvider,
            externalStoragePathProvider,
            diaryEntryMapper
        );
    }
}