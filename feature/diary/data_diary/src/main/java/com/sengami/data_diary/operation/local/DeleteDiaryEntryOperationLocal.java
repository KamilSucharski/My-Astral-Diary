package com.sengami.data_diary.operation.local;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.logger.Logger;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;

public final class DeleteDiaryEntryOperationLocal extends BaseOperation<Boolean> implements DeleteDiaryEntryOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @Nullable
    private DiaryEntry diaryEntry;

    @Override
    @NotNull
    public DeleteDiaryEntryOperation withDiaryEntry(@NotNull final DiaryEntry diaryEntry) {
        this.diaryEntry = diaryEntry;
        return this;
    }

    public DeleteDiaryEntryOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                          @NotNull final WithErrorHandler withErrorHandler,
                                          @NotNull final WithLoadingIndicator withLoadingIndicator,
                                          @NotNull final Logger logger,
                                          @NotNull final DatabaseConnectionProvider databaseConnectionProvider) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator, logger);
        this.databaseConnectionProvider = databaseConnectionProvider;
    }

    @Override
    protected Observable<Boolean> getObservable() {
        return Observable.fromCallable(() -> {
            if (diaryEntry == null) {
                throw new IllegalArgumentException("DiaryEntry has not been set on CreateOrUpdateDiaryEntryOperationLocal");
            }

            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            diaryEntryDao.deleteById(diaryEntry.getId());
            connectionSource.close();
            return true;
        });
    }
}