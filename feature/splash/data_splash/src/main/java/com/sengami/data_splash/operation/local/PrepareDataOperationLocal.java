package com.sengami.data_splash.operation.local;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sengami.data_base.util.ConnectionSourceProvider;
import com.sengami.data_splash.dbo.DiaryEntryDBO;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.util.Constants;
import com.sengami.domain_base.util.ErrorHandler;
import com.sengami.domain_base.util.ReactiveSchedulers;
import com.sengami.domain_splash.operation.PrepareDataOperation;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class PrepareDataOperationLocal extends BaseOperation<Boolean> implements PrepareDataOperation {

    @NotNull
    private final ConnectionSourceProvider connectionSourceProvider;

    public PrepareDataOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                     @NotNull final ErrorHandler errorHandler,
                                     @NotNull final ConnectionSourceProvider connectionSourceProvider) {
        super(reactiveSchedulers, errorHandler);
        this.connectionSourceProvider = connectionSourceProvider;
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
            final ConnectionSource connectionSource = connectionSourceProvider.provide();
            TableUtils.createTableIfNotExists(connectionSource, DiaryEntryDBO.class);
            connectionSource.close();
            return true;
        });
    }

    private Observable<Boolean> splashScreenDelay() {
        return Observable
            .just(true)
            .delay(Constants.MINIMAL_SPLASH_SCREEN_DURATION_MILLISECONDS, TimeUnit.MILLISECONDS);
    }
}