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
import com.sengami.domain_diary.operation.GetDiaryEntriesGroupedByDateOperation;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public final class GetDiaryEntriesGroupedByDateOperationLocal
    extends BaseOperation<Map<LocalDate, List<DiaryEntry>>>
    implements GetDiaryEntriesGroupedByDateOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final Mapper<DiaryEntryDBO, DiaryEntry> mapper;

    public GetDiaryEntriesGroupedByDateOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                                      @NotNull final WithErrorHandler withErrorHandler,
                                                      @NotNull final WithLoadingIndicator withLoadingIndicator,
                                                      @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                                      @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator);
        this.databaseConnectionProvider = databaseConnectionProvider;
        this.mapper = mapper;
    }

    @Override
    protected Observable<Map<LocalDate, List<DiaryEntry>>> getObservable() {
        return Observable.fromCallable(() -> {
            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            final List<DiaryEntryDBO> entries = diaryEntryDao.queryForAll();
            final Map<LocalDate, List<DiaryEntry>> result = Stream.of(entries)
                .map(mapper::toModel)
                .groupBy(DiaryEntry::getDate)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            connectionSource.close();
            return result;
        });
    }
}