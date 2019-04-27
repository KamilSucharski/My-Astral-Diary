package com.sengami.gui_diary.di.module;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_diary.operation.local.CreateOrUpdateDiaryEntryOperationLocal;
import com.sengami.data_diary.operation.local.DeleteDiaryEntryOperationLocal;
import com.sengami.data_diary.operation.local.GetDiaryEntriesOperationLocal;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.logger.Logger;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;
import com.sengami.domain_diary.operation.CreateOrUpdateDiaryEntryOperation;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;
import com.sengami.domain_diary.operation.GetDiaryEntriesOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    GetDiaryEntriesOperation getHelloWorldOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                    @NotNull final WithErrorHandler withErrorHandler,
                                                    @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                    @NotNull final Logger logger,
                                                    @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                    @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        return new GetDiaryEntriesOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            logger,
            databaseConnectionProvider,
            mapper
        );
    }

    @Provides
    @NotNull
    CreateOrUpdateDiaryEntryOperation createOrUpdateDiaryEntryOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                                        @NotNull final WithErrorHandler withErrorHandler,
                                                                        @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                                        @NotNull final Logger logger,
                                                                        @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                                        @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        return new CreateOrUpdateDiaryEntryOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            logger,
            databaseConnectionProvider,
            mapper
        );
    }

    @Provides
    @NotNull
    DeleteDiaryEntryOperation deleteDiaryEntryOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                        @NotNull final WithErrorHandler withErrorHandler,
                                                        @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                        @NotNull final Logger logger,
                                                        @NotNull final DatabaseConnectionProvider databaseConnectionProvider) {
        return new DeleteDiaryEntryOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            logger,
            databaseConnectionProvider
        );
    }
}