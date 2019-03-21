package com.sengami.data_settings.operation.local;

import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_base.util.InternalStoragePathProvider;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
import com.sengami.domain_settings.operation.RestoreFromBackupOperation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

import io.reactivex.Observable;

public final class RestoreFromBackupOperationLocal extends BaseOperation<Boolean> implements RestoreFromBackupOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final InternalStoragePathProvider internalStoragePathProvider;
    @Nullable
    private File backup;

    public RestoreFromBackupOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                           @NotNull final WithErrorHandler withErrorHandler,
                                           @NotNull final WithLoadingIndicator withLoadingIndicator,
                                           @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                           @NotNull final InternalStoragePathProvider internalStoragePathProvider) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator);
        this.databaseConnectionProvider = databaseConnectionProvider;
        this.internalStoragePathProvider = internalStoragePathProvider;
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
//            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
//            TableUtils.createTableIfNotExists(connectionSource, DiaryEntryDBO.class);
//            connectionSource.close();
//            return true;
            if (backup == null) {
                throw new IllegalArgumentException("[File backup] has not been set in RestoreFromBackupOperationLocal");
            }

            return true;
        });
    }
}