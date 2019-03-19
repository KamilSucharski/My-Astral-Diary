package com.sengami.data_diary.operation.local;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_diary.dbo.DiaryEntryDBO;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.domain_diary.operation.CreateOrUpdateDiaryEntryOperation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;

public class CreateOrUpdateDiaryEntryOperationLocal extends BaseOperation<Boolean> implements CreateOrUpdateDiaryEntryOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final Mapper<DiaryEntryDBO, DiaryEntry> mapper;
    @Nullable
    private DiaryEntry diaryEntry;

    @Override
    @NotNull
    public CreateOrUpdateDiaryEntryOperation withDiaryEntry(@NotNull final DiaryEntry diaryEntry) {
        this.diaryEntry = diaryEntry;
        return this;
    }

    public CreateOrUpdateDiaryEntryOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                  @NotNull final WithErrorHandler withErrorHandler,
                                                  @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                  @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                  @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator);
        this.databaseConnectionProvider = databaseConnectionProvider;
        this.mapper = mapper;
    }

    @Override
    protected Observable<Boolean> getObservable() {
        return Observable.fromCallable(() -> {
            if (diaryEntry == null) {
                throw new IllegalArgumentException("DiaryEntry has not been set on CreateOrUpdateDiaryEntryOperationLocal");
            }

            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            final DiaryEntryDBO diaryEntryDBO = mapper.toDBO(diaryEntry);
            diaryEntryDao.createOrUpdate(diaryEntryDBO);
            connectionSource.close();
            return true;
        });
    }
}