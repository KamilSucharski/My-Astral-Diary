package com.sengami.data_diary.operation.local;

import com.annimon.stream.Stream;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.logger.Logger;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;
import com.sengami.domain_diary.operation.GetDiaryEntriesOperation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;

public final class GetDiaryEntriesOperationLocal
    extends BaseOperation<List<DiaryEntry>>
    implements GetDiaryEntriesOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final Mapper<DiaryEntryDBO, DiaryEntry> mapper;

    public GetDiaryEntriesOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                         @NotNull final WithErrorHandler withErrorHandler,
                                         @NotNull final WithLoadingIndicator withLoadingIndicator,
                                         @NotNull final Logger logger,
                                         @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                         @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator, logger);
        this.databaseConnectionProvider = databaseConnectionProvider;
        this.mapper = mapper;
    }

    @Override
    protected Observable<List<DiaryEntry>> getObservable() {
        return Observable.fromCallable(() -> {
            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            final List<DiaryEntryDBO> entries = diaryEntryDao.queryForAll();
            final List<DiaryEntry> result = Stream.of(entries)
                .map(mapper::toModel)
                .toList();
            connectionSource.close();
            return result;
        });
    }
}