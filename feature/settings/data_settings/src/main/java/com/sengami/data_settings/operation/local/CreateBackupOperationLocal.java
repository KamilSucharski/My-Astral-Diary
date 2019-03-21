package com.sengami.data_settings.operation.local;

import com.sengami.data_base.util.InternalStoragePathProvider;
import com.sengami.date.DateFormatter;
import com.sengami.domain_base.Constants;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
import com.sengami.domain_settings.operation.CreateBackupOperation;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import io.reactivex.Observable;

public final class CreateBackupOperationLocal extends BaseOperation<File> implements CreateBackupOperation {

    private static final String DATABASE_BACKUP_NAME_FORMAT = "my_astral_diary_%1$s.db";
    private static final String DATABASE_BACKUP_DATE_FORMAT = "ddMMyyyy_hhmm";

    @NotNull
    private final InternalStoragePathProvider internalStoragePathProvider;

    public CreateBackupOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                      @NotNull final WithErrorHandler withErrorHandler,
                                      @NotNull final WithLoadingIndicator withLoadingIndicator,
                                      @NotNull final InternalStoragePathProvider internalStoragePathProvider) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator);
        this.internalStoragePathProvider = internalStoragePathProvider;
    }

    @Override
    protected Observable<File> getObservable() {
        return Observable.fromCallable(() -> {
            final File database = new File(internalStoragePathProvider.provide() + Constants.DATABASE_PATH);
            final FileInputStream inputStream = new FileInputStream(database);
            final File backup = new File(createBackupName());
            final OutputStream output = new FileOutputStream(backup);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            inputStream.close();
            return backup;
        });
    }

    @NotNull
    private String createBackupName() {
        return String.format(
            DATABASE_BACKUP_NAME_FORMAT,
            DateFormatter.format(new Date(), DATABASE_BACKUP_DATE_FORMAT)
        );
    }
}