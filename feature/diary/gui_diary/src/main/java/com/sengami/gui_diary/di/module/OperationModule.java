package com.sengami.gui_diary.di.module;

import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.ConnectionSourceProvider;
import com.sengami.data_diary.dbo.DiaryEntryDBO;
import com.sengami.data_diary.operation.local.CreateOrUpdateDiaryEntryOperationLocal;
import com.sengami.data_diary.operation.local.DeleteDiaryEntryOperationLocal;
import com.sengami.data_diary.operation.local.GetDiaryEntryListOperationLocal;
import com.sengami.domain_base.util.ErrorHandler;
import com.sengami.domain_base.util.ReactiveSchedulers;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.domain_diary.operation.CreateOrUpdateDiaryEntryOperation;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;
import com.sengami.domain_diary.operation.GetDiaryEntryListOperation;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class OperationModule {

    @Provides
    @NotNull
    GetDiaryEntryListOperation getHelloWorldOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                      @NotNull final ErrorHandler errorHandler,
                                                      @NotNull final ConnectionSourceProvider connectionSourceProvider,
                                                      @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        return new GetDiaryEntryListOperationLocal(
            reactiveSchedulers,
            errorHandler,
            connectionSourceProvider,
            mapper
        );
    }

    @Provides
    @NotNull
    CreateOrUpdateDiaryEntryOperation createOrUpdateDiaryEntryOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                                        @NotNull final ErrorHandler errorHandler,
                                                                        @NotNull final ConnectionSourceProvider connectionSourceProvider,
                                                                        @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        return new CreateOrUpdateDiaryEntryOperationLocal(
            reactiveSchedulers,
            errorHandler,
            connectionSourceProvider,
            mapper
        );
    }

    @Provides
    @NotNull
    DeleteDiaryEntryOperation deleteDiaryEntryOperation(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                        @NotNull final ErrorHandler errorHandler,
                                                        @NotNull final ConnectionSourceProvider connectionSourceProvider) {
        return new DeleteDiaryEntryOperationLocal(
            reactiveSchedulers,
            errorHandler,
            connectionSourceProvider
        );
    }
}