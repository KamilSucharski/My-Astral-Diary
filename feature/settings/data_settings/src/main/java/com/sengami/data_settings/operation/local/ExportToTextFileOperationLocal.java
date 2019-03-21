package com.sengami.data_settings.operation.local;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_base.util.ExternalStoragePathProvider;
import com.sengami.data_diary.dbo.DiaryEntryDBO;
import com.sengami.date.DateFormatter;
import com.sengami.domain_base.Constants;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
import com.sengami.domain_settings.operation.ExportToTextFileOperation;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

public final class ExportToTextFileOperationLocal extends BaseOperation<File> implements ExportToTextFileOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final ExternalStoragePathProvider externalStoragePathProvider;

    public ExportToTextFileOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                          @NotNull final WithErrorHandler withErrorHandler,
                                          @NotNull final WithLoadingIndicator withLoadingIndicator,
                                          @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                          @NotNull final ExternalStoragePathProvider externalStoragePathProvider) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator);
        this.databaseConnectionProvider = databaseConnectionProvider;
        this.externalStoragePathProvider = externalStoragePathProvider;
    }

    @Override
    protected Observable<File> getObservable() {
        return Observable.fromCallable(() -> {
            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
            final Dao<DiaryEntryDBO, Integer> diaryEntryDao = DaoManager.createDao(connectionSource, DiaryEntryDBO.class);
            final List<DiaryEntryDBO> entries = diaryEntryDao.queryForAll();
            final File file = createTextExportFile();
            final FileWriter fileWriter = new FileWriter(file);
            final PrintWriter printWriter = new PrintWriter(fileWriter);

            for (final DiaryEntryDBO diaryEntryDBO : entries) {
                printWriter.print(DateFormatter.format(diaryEntryDBO.getDate(), Constants.DISPLAYED_DATE_FORMAT));
                printWriter.print("\n");
                printWriter.print(diaryEntryDBO.getTitle());
                printWriter.print("\n\n");
                printWriter.print(diaryEntryDBO.getBody());
                printWriter.print("\n\n==========\n\n");
            }

            printWriter.close();
            connectionSource.close();
            return file;
        });
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