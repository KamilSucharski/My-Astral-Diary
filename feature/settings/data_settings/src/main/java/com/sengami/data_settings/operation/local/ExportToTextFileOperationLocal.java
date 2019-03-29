package com.sengami.data_settings.operation.local;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.date.DateFormatter;
import com.sengami.domain_base.Constants;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.logger.Logger;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;
import com.sengami.domain_settings.operation.ExportToTextFileOperation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public final class ExportToTextFileOperationLocal extends BaseOperation<Boolean> implements ExportToTextFileOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final Mapper<DiaryEntryDBO, DiaryEntry> mapper;
    @Nullable
    private OutputStream textExportFileOutputStream;

    public ExportToTextFileOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
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
    @NotNull
    public ExportToTextFileOperation withTextExportFileOutputStream(@NotNull final OutputStream textExportFileOutputStream) {
        this.textExportFileOutputStream = textExportFileOutputStream;
        return this;
    }

    @Override
    protected Observable<Boolean> getObservable() {
        return Observable.fromCallable(() -> {
            final Map<LocalDate, List<DiaryEntry>> entries = getDiaryEntriesGroupedByDate();
            writeEntriesToTextFile(entries);
            return true;
        });
    }

    @NotNull
    private Map<LocalDate, List<DiaryEntry>> getDiaryEntriesGroupedByDate() throws IOException, SQLException {
        final ConnectionSource connectionSource = databaseConnectionProvider.provide();
        final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
        final List<DiaryEntryDBO> entries = diaryEntryDao.queryForAll();
        final Map<LocalDate, List<DiaryEntry>> result = Stream.of(entries)
            .map(mapper::toModel)
            .groupBy(DiaryEntry::getDate)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        connectionSource.close();
        return result;
    }

    private void writeEntriesToTextFile(@NotNull final Map<LocalDate, List<DiaryEntry>> entries) throws IOException {
        if (textExportFileOutputStream == null) {
            throw new IllegalArgumentException("[OutputStream textExportFileOutputStream] has not been set in ExportToTextFileOperationLocal");
        }

        final PrintWriter printWriter = new PrintWriter(textExportFileOutputStream);

        for (final Map.Entry<LocalDate, List<DiaryEntry>> diaryEntryGroup : entries.entrySet()) {
            printWriter.printf(
                Constants.TEXT_EXPORT_DATE_FORMAT,
                DateFormatter.format(diaryEntryGroup.getKey().toDate(), Constants.DISPLAYED_DATE_FORMAT)
            );

            for (final DiaryEntry diaryEntry : diaryEntryGroup.getValue()) {
                printWriter.printf(
                    Constants.TEXT_EXPORT_ENTRY_FORMAT,
                    diaryEntry.getTitle(),
                    diaryEntry.getBody()
                );
            }
        }

        printWriter.close();
        textExportFileOutputStream.flush();
        textExportFileOutputStream.close();
    }
}