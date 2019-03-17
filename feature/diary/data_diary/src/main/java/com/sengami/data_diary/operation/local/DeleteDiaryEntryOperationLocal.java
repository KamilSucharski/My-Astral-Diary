package com.sengami.data_diary.operation.local;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.util.ConnectionSourceProvider;
import com.sengami.data_diary.dbo.DiaryEntryDBO;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.util.ErrorHandler;
import com.sengami.domain_base.util.ReactiveSchedulers;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;

public class DeleteDiaryEntryOperationLocal extends BaseOperation<Boolean> implements DeleteDiaryEntryOperation {

    @NotNull
    private final ConnectionSourceProvider connectionSourceProvider;
    @Nullable
    private DiaryEntry diaryEntry;

    @Override
    @NotNull
    public DeleteDiaryEntryOperation withDiaryEntry(@NotNull final DiaryEntry diaryEntry) {
        this.diaryEntry = diaryEntry;
        return this;
    }

    public DeleteDiaryEntryOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                          @NotNull final ErrorHandler errorHandler,
                                          @NotNull final ConnectionSourceProvider connectionSourceProvider) {
        super(reactiveSchedulers, errorHandler);
        this.connectionSourceProvider = connectionSourceProvider;
    }

    @Override
    protected Observable<Boolean> getObservable() {
        return Observable.fromCallable(() -> {
            if (diaryEntry == null) {
                throw new IllegalArgumentException("DiaryEntry has not been set on CreateOrUpdateDiaryEntryOperationLocal");
            }

            final ConnectionSource connectionSource = connectionSourceProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            diaryEntryDao.deleteById(diaryEntry.getId());
            connectionSource.close();
            return true;
        });
    }
}