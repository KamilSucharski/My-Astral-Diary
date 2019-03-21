package com.sengami.data_settings.operation.local;

import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_base.util.InternalStoragePathProvider;
import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.schedulers.ReactiveSchedulers;
import com.sengami.domain_settings.operation.CreateBackupOperation;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import io.reactivex.Observable;

public final class CreateBackupOperationLocal extends BaseOperation<File> implements CreateBackupOperation {

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;
    @NotNull
    private final InternalStoragePathProvider internalStoragePathProvider;

    public CreateBackupOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                      @NotNull final WithErrorHandler withErrorHandler,
                                      @NotNull final WithLoadingIndicator withLoadingIndicator,
                                      @NotNull final DatabaseConnectionProvider databaseConnectionProvider,
                                      @NotNull final InternalStoragePathProvider internalStoragePathProvider) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator);
        this.databaseConnectionProvider = databaseConnectionProvider;
        this.internalStoragePathProvider = internalStoragePathProvider;
    }

    @Override
    protected Observable<File> getObservable() {
        return Observable.fromCallable(() -> {
//            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
//            TableUtils.createTableIfNotExists(connectionSource, DiaryEntryDBO.class);
//            connectionSource.close();
//            return true;
            return new File("asd.txt");
        });
    }
}