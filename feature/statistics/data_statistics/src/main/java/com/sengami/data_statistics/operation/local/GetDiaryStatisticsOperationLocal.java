package com.sengami.data_statistics.operation.local;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.model.DiaryStatistics;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
import com.sengami.domain_statistics.operation.GetDiaryStatisticsOperation;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public final class GetDiaryStatisticsOperationLocal
    extends BaseOperation<DiaryStatistics>
    implements GetDiaryStatisticsOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final Mapper<DiaryEntryDBO, DiaryEntry> mapper;

    public GetDiaryStatisticsOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                            @NotNull final WithErrorHandler withErrorHandler,
                                            @NotNull final WithLoadingIndicator withLoadingIndicator,
                                            @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                            @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator);
        this.databaseConnectionProvider = databaseConnectionProvider;
        this.mapper = mapper;
    }

    @Override
    protected Observable<DiaryStatistics> getObservable() {
        return Observable.fromCallable(() -> {
            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            final List<DiaryEntryDBO> diaryEntryDBOList = diaryEntryDao.queryForAll();
            connectionSource.close();
            final List<DiaryEntry> entries = Stream
                .of(diaryEntryDBOList)
                .map(mapper::toModel)
                .collect(Collectors.toList());

            final DiaryStatistics diaryStatistics = new DiaryStatistics();
            diaryStatistics.setNumberOfEntriesByDate(getNumberOfEntriesByDate(entries));
            diaryStatistics.setTotalEntries(entries.size());
            diaryStatistics.setLongestEntryCharacterCount(getLongestCharacterCountInEntry(entries));
            return diaryStatistics;
        });
    }

    @NotNull
    private Map<LocalDate, Integer> getNumberOfEntriesByDate(@NotNull final List<DiaryEntry> entries) {
        return Stream.of(entries)
            .groupBy(DiaryEntry::getDate)
            .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().size()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private int getLongestCharacterCountInEntry(@NotNull final List<DiaryEntry> entries) {
        int maxCharacterCount = 0;
        for (final DiaryEntry diaryEntry : entries) {
            final int characterCount = diaryEntry.getTitle().length() + diaryEntry.getBody().length();
            if (characterCount > maxCharacterCount) {
                maxCharacterCount = characterCount;
            }
        }
        return maxCharacterCount;
    }
}