package com.sengami.gui_statistics.di.module;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_statistics.operation.local.GetDiaryStatisticsOperationLocal;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
import com.sengami.domain_statistics.operation.GetDiaryStatisticsOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    GetDiaryStatisticsOperation getDiaryStatisticsOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                            @NotNull final WithErrorHandler withErrorHandler,
                                                            @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                            @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                            @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> diaryEntryMapper) {
        return new GetDiaryStatisticsOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            databaseConnectionProvider,
            diaryEntryMapper
        );
    }
}