package com.sengami.gui_statistics.di.module;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_statistics.operation.local.GetStatisticsOperationLocal;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.logger.Logger;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;
import com.sengami.domain_statistics.operation.GetStatisticsOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    GetStatisticsOperation getDiaryStatisticsOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                       @NotNull final WithErrorHandler withErrorHandler,
                                                       @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                       @NotNull final Logger logger,
                                                       @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                       @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> diaryEntryMapper) {
        return new GetStatisticsOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            logger,
            databaseConnectionProvider,
            diaryEntryMapper
        );
    }
}