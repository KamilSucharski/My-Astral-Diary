package com.sengami.data_statistics.operation.local;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.model.Statistics;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.logger.Logger;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;
import com.sengami.domain_statistics.operation.GetStatisticsOperation;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public final class GetStatisticsOperationLocal
    extends BaseOperation<Statistics>
    implements GetStatisticsOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final Mapper<DiaryEntryDBO, DiaryEntry> mapper;

    public GetStatisticsOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
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
    protected Observable<Statistics> getObservable() {
        return Observable.fromCallable(() -> {
            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            final List<DiaryEntryDBO> diaryEntryDBOList = diaryEntryDao.queryForAll();
            connectionSource.close();

            final List<DiaryEntry> entries = Stream
                .of(diaryEntryDBOList)
                .map(mapper::toModel)
                .collect(Collectors.toList());

            final Statistics statistics = new Statistics();
            if (!entries.isEmpty()) {
                statistics.setEntryDatesGroupedByYears(createDatesWithEntriesGroupedByYear(entries));
                statistics.setYearWithMostEntries(calculateYearWithMostEntries(entries));
                statistics.setTotalEntries(entries.size());
                statistics.setLongestEntryCharacterCount(calculateLongestCharacterCountInEntry(entries));
                statistics.setAverageEntriesPerDay(calculateAverageEntriesPerDay(entries));
            }
            return statistics;
        });
    }

    private Map<Integer, List<LocalDate>> createDatesWithEntriesGroupedByYear(@NotNull final List<DiaryEntry> entries) {
        return Stream.of(entries)
            .map(DiaryEntry::getDate)
            .groupBy(LocalDate::getYear)
            .sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey()))
            .reduce(new LinkedHashMap<>(), ((accumulator, entry) -> {
                accumulator.put(entry.getKey(), entry.getValue());
                return accumulator;
            }));
    }

    private int calculateYearWithMostEntries(@NotNull final List<DiaryEntry> entries) {
        return Stream.of(entries)
            .map(DiaryEntry::getDate)
            .groupBy(LocalDate::getYear)
            .map(group -> new AbstractMap.SimpleEntry<>(group.getKey(), group.getValue().size()))
            .sorted((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
            .findLast()
            .map(AbstractMap.SimpleEntry::getKey)
            .orElse(0);
    }

    private int calculateLongestCharacterCountInEntry(@NotNull final List<DiaryEntry> entries) {
        int maxCharacterCount = 0;
        for (final DiaryEntry diaryEntry : entries) {
            final int titleCharacterCount = diaryEntry.getTitle().replaceAll("\n", "").length();
            final int bodyCharacterCount = diaryEntry.getBody().replaceAll("\n", "").length();
            final int characterCount = titleCharacterCount + bodyCharacterCount;
            if (characterCount > maxCharacterCount) {
                maxCharacterCount = characterCount;
            }
        }
        return maxCharacterCount;
    }

    private double calculateAverageEntriesPerDay(@NotNull final List<DiaryEntry> entries) {
        final double totalEntries = entries.size();
        final double documentedDays = Stream.of(entries)
            .map(DiaryEntry::getDate)
            .collect(Collectors.toSet())
            .size();
        return new BigDecimal(totalEntries / documentedDays)
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
    }
}