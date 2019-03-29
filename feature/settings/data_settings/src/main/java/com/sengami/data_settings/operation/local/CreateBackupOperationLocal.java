package com.sengami.data_settings.operation.local;

import com.sengami.data_base.util.DatabaseFileProvider;
import com.sengami.data_base.util.ExternalStoragePathProvider;
import com.sengami.date.DateFormatter;
import com.sengami.domain_base.Constants;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.logger.Logger;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;
import com.sengami.domain_settings.operation.CreateBackupOperation;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import io.reactivex.Observable;

public final class CreateBackupOperationLocal extends BaseOperation<File> implements CreateBackupOperation {

    @NotNull
    private final DatabaseFileProvider databaseFileProvider;
    @NotNull
    private final ExternalStoragePathProvider externalStoragePathProvider;

    public CreateBackupOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                      @NotNull final WithErrorHandler withErrorHandler,
                                      @NotNull final WithLoadingIndicator withLoadingIndicator,
                                      @NotNull final Logger logger,
                                      @NotNull final DatabaseFileProvider databaseFileProvider,
                                      @NotNull final ExternalStoragePathProvider externalStoragePathProvider) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator, logger);
        this.databaseFileProvider = databaseFileProvider;
        this.externalStoragePathProvider = externalStoragePathProvider;
    }

    @Override
    protected Observable<File> getObservable() {
        return Observable.fromCallable(() -> {
            final File database = databaseFileProvider.provide(Constants.DATABASE_NAME);
            final FileInputStream inputStream = new FileInputStream(database);
            final File backup = createBackupFile();
            final OutputStream outputStream = new FileOutputStream(backup);
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return backup;
        });
    }

    @NotNull
    private File createBackupFile() throws IOException {
        final String fileName = String.format(
            Constants.DATABASE_BACKUP_NAME_FORMAT,
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