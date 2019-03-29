package com.sengami.data_settings.operation.local;

import com.sengami.data_base.util.DatabaseFileProvider;
import com.sengami.domain_base.Constants;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.logger.Logger;
import com.sengami.domain_base.operation.schedulers.ReactiveSchedulers;
import com.sengami.domain_settings.operation.RestoreFromBackupOperation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;

public final class RestoreFromBackupOperationLocal extends BaseOperation<Boolean> implements RestoreFromBackupOperation {

    @NotNull
    private final DatabaseFileProvider databaseFileProvider;
    @Nullable
    private File backup;

    public RestoreFromBackupOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                           @NotNull final WithErrorHandler withErrorHandler,
                                           @NotNull final WithLoadingIndicator withLoadingIndicator,
                                           @NotNull final Logger logger,
                                           @NotNull final DatabaseFileProvider databaseFileProvider) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator, logger);
        this.databaseFileProvider = databaseFileProvider;
    }

    @Override
    @NotNull
    public RestoreFromBackupOperation withBackupFile(@NotNull final File backup) {
        this.backup = backup;
        return this;
    }

    @Override
    protected Observable<Boolean> getObservable() {
        return Observable.fromCallable(() -> {
            if (backup == null) {
                throw new IllegalArgumentException("[File backup] has not been set in RestoreFromBackupOperationLocal");
            }

            final File database = databaseFileProvider.provide(Constants.DATABASE_NAME);
            final InputStream inputStream = new BufferedInputStream(new FileInputStream(backup));
            final OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(database));
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        });
    }
}