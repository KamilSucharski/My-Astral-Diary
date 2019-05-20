package com.sengami.gui_statistics.di.module;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_statistics.operation.local.GetStatisticsOperationLocal;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.configuration.OperationConfiguration;
import com.sengami.domain_statistics.operation.GetStatisticsOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    GetStatisticsOperation getDiaryStatisticsOperation(@NotNull final OperationConfiguration operationConfiguration,
                                                       @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                       @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> diaryEntryMapper) {
        return new GetStatisticsOperationLocal(
            operationConfiguration,
            databaseConnectionProvider,
            diaryEntryMapper
        );
    }
}