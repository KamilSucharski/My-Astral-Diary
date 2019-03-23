package com.sengami.data_settings.operation.local;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_base.util.ExternalStoragePathProvider;
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
import org.joda.time.LocalDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public final class ExportToTextFileOperationLocal extends BaseOperation<File> implements ExportToTextFileOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final ExternalStoragePathProvider externalStoragePathProvider;
    @NotNull
    private final Mapper<DiaryEntryDBO, DiaryEntry> mapper;

    public ExportToTextFileOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                          @NotNull final WithErrorHandler withErrorHandler,
                                          @NotNull final WithLoadingIndicator withLoadingIndicator,
                                          @NotNull final Logger logger,
                                          @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                          @NotNull final ExternalStoragePathProvider externalStoragePathProvider,
                                          @NotNull final Mapper<DiaryEntryDBO, DiaryEntry> mapper) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator, logger);
        this.databaseConnectionProvider = databaseConnectionProvider;
        this.externalStoragePathProvider = externalStoragePathProvider;
        this.mapper = mapper;
    }

    @Override
    protected Observable<File> getObservable() {
        return Observable.fromCallable(() -> {
            final Map<LocalDate, List<DiaryEntry>> entries = getDiaryEntriesGroupedByDate();
            return writeEntriesToTextFile(entries);
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

    @NotNull
    private File writeEntriesToTextFile(@NotNull final Map<LocalDate, List<DiaryEntry>> entries) throws IOException {
        final File file = createTextExportFile();
        final FileWriter fileWriter = new FileWriter(file);
        final PrintWriter printWriter = new PrintWriter(fileWriter);

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
        return file;
    }

    @NotNull
    private File createTextExportFile() throws IOException {
        final String fileName = String.format(
            Constants.TEXT_EXPORT_NAME_FORMAT,
            DateFormatter.format(new Date(), Constants.FILE_DATE_FORMAT)
        );
        final String filePath = externalStoragePathProvider.provide() + "/" + fileName;
        final File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}