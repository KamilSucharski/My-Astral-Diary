package com.sengami.data_diary.operation.local;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
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
import com.sengami.domain_diary.operation.GetDiaryEntryListOperation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;

public final class GetDiaryEntryListOperationLocal extends BaseOperation<List<DiaryEntry>> implements GetDiaryEntryListOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final Mapper<DiaryEntryDBO, DiaryEntry> mapper;

    public GetDiaryEntryListOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                           @NotNull final WithErrorHandler withErrorHandler,
                                           @NotNull final WithLoadingIndicator withLoadingIndicator,
                                           @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                           @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator);
        this.databaseConnectionProvider = databaseConnectionProvider;
        this.mapper = mapper;
    }

    @Override
    protected Observable<List<DiaryEntry>> getObservable() {
        return Observable.fromCallable(() -> {
            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            final List<DiaryEntryDBO> entries = diaryEntryDao.queryForAll();
            final List<DiaryEntry> result = Stream.of(entries).map(mapper::toModel).collect(Collectors.toList());
            connectionSource.close();
            return result;
        });
    }
}