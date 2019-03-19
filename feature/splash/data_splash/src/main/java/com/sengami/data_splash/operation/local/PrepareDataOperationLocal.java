package com.sengami.data_splash.operation.local;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.data_diary.dbo.DiaryEntryDBO;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.util.ReactiveSchedulers;
import com.sengami.domain_base.util.error.WithErrorHandler;
import com.sengami.domain_base.util.loading.WithLoadingIndicator;
import com.sengami.domain_splash.operation.PrepareDataOperation;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class PrepareDataOperationLocal extends BaseOperation<Boolean> implements PrepareDataOperation {

    public static final int MINIMAL_SPLASH_SCREEN_DURATION_MILLISECONDS = 1000;

    @NotNull
    private final DatabaseConnectionProvider databaseConnectionProvider;

    public PrepareDataOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                     @NotNull final WithErrorHandler withErrorHandler,
                                     @NotNull final WithLoadingIndicator withLoadingIndicator,
                                     @NotNull final DatabaseConnectionProvider databaseConnectionProvider) {
        super(reactiveSchedulers, withErrorHandler, withLoadingIndicator);
        this.databaseConnectionProvider = databaseConnectionProvider;
    }

    @Override
    protected Observable<Boolean> getObservable() {
        return Observable.zip(
            initializeDatabase(),
            splashScreenDelay(),
            (a, b) -> a && b
        );
    }

    private Observable<Boolean> initializeDatabase() {
        return Observable.fromCallable(() -> {
            final ConnectionSource connectionSource = databaseConnectionProvider.provide();
            TableUtils.createTableIfNotExists(connectionSource, DiaryEntryDBO.class);
            connectionSource.close();
            return true;
        });
    }

    private Observable<Boolean> splashScreenDelay() {
        return Observable
            .just(true)
            .delay(MINIMAL_SPLASH_SCREEN_DURATION_MILLISECONDS, TimeUnit.MILLISECONDS);
    }
}