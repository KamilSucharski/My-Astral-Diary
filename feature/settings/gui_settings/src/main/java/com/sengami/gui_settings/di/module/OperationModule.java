package com.sengami.gui_settings.di.module;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_base.util.DatabaseFileProvider;
import com.sengami.data_settings.operation.local.CreateBackupOperationLocal;
import com.sengami.data_settings.operation.local.ExportToTextFileOperationLocal;
import com.sengami.data_settings.operation.local.RestoreFromBackupOperationLocal;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.configuration.OperationConfiguration;
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
    CreateBackupOperation createBackupOperation(@NotNull final OperationConfiguration operationConfiguration,
                                                @NotNull final DatabaseFileProvider databaseFileProvider) {
        return new CreateBackupOperationLocal(
            operationConfiguration,
            databaseFileProvider
        );
    }

    @Provides
    @NotNull
    RestoreFromBackupOperation restoreFromBackupOperation(@NotNull final OperationConfiguration operationConfiguration,
                                                          @NotNull final DatabaseFileProvider databaseFileProvider) {
        return new RestoreFromBackupOperationLocal(
            operationConfiguration,
            databaseFileProvider
        );
    }

    @Provides
    @NotNull
    ExportToTextFileOperation exportToTextFileOperation(@NotNull final OperationConfiguration operationConfiguration,
                                                        @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                        @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> diaryEntryMapper) {
        return new ExportToTextFileOperationLocal(
            operationConfiguration,
            databaseConnectionProvider,
            diaryEntryMapper
        );
    }
}