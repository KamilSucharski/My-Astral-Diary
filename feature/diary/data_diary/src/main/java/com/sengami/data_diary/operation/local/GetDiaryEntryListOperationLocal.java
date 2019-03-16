package com.sengami.data_diary.operation.local;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.ConnectionSourceProvider;
import com.sengami.data_diary.dbo.DiaryEntryDBO;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.util.ErrorHandler;
import com.sengami.domain_base.util.ReactiveSchedulers;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.domain_diary.operation.GetDiaryEntryListOperation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;

public class GetDiaryEntryListOperationLocal extends BaseOperation<List<DiaryEntry>> implements GetDiaryEntryListOperation {

    @NotNull
    private final ConnectionSourceProvider connectionSourceProvider;
    @NotNull
    private final Mapper<DiaryEntryDBO, DiaryEntry> mapper;

    public GetDiaryEntryListOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                           @NotNull final ErrorHandler errorHandler,
                                           @NotNull final ConnectionSourceProvider connectionSourceProvider,
                                           @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        super(reactiveSchedulers, errorHandler);
        this.connectionSourceProvider = connectionSourceProvider;
        this.mapper = mapper;
    }

    @Override
    protected Observable<List<DiaryEntry>> getObservable() {
        return Observable.fromCallable(() -> {
            final ConnectionSource connectionSource = connectionSourceProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            final List<DiaryEntryDBO> entries = diaryEntryDao.queryForAll();
            final List<DiaryEntry> result = Stream.of(entries).map(mapper::toModel).collect(Collectors.toList());
            connectionSource.close();
            return result;
        });
    }
}