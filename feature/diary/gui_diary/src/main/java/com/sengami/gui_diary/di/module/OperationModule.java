package com.sengami.gui_diary.di.module;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_diary.operation.local.CreateOrUpdateDiaryEntryOperationLocal;
import com.sengami.data_diary.operation.local.DeleteDiaryEntryOperationLocal;
import com.sengami.data_diary.operation.local.GetDiaryEntriesGroupedByDateOperationLocal;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
import com.sengami.domain_diary.operation.CreateOrUpdateDiaryEntryOperation;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;
import com.sengami.domain_diary.operation.GetDiaryEntriesGroupedByDateOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    GetDiaryEntriesGroupedByDateOperation getHelloWorldOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                                 @NotNull final WithErrorHandler withErrorHandler,
                                                                 @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                                 @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                                 @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        return new GetDiaryEntriesGroupedByDateOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            databaseConnectionProvider,
            mapper
        );
    }

    @Provides
    @NotNull
    CreateOrUpdateDiaryEntryOperation createOrUpdateDiaryEntryOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                                        @NotNull final WithErrorHandler withErrorHandler,
                                                                        @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                                        @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                                        @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        return new CreateOrUpdateDiaryEntryOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            databaseConnectionProvider,
            mapper
        );
    }

    @Provides
    @NotNull
    DeleteDiaryEntryOperation deleteDiaryEntryOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                        @NotNull final WithErrorHandler withErrorHandler,
                                                        @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                        @NotNull final DatabaseConnectionProvider databaseConnectionProvider) {
        return new DeleteDiaryEntryOperationLocal(
            reactiveSchedulers,
            withErrorHandler,
            withLoadingIndicator,
            databaseConnectionProvider
        );
    }
}