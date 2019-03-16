package com.sengami.data_main.operation.local;

import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.util.ErrorHandler;
import com.sengami.domain_base.util.ReactiveSchedulers;
import com.sengami.domain_main.operation.GetHelloWorldOperation;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public class GetHelloWorldOperationLocal extends BaseOperation<String> implements GetHelloWorldOperation {

    public GetHelloWorldOperationLocal(@NotNull final ReactiveSchedulers reactiveSchedulers,
                                       @NotNull final ErrorHandler errorHandler) {
        super(reactiveSchedulers, errorHandler);
    }

    @Override
    protected Observable<String> getObservable() {
        return Observable.just("Witaj swiecie");
    }
}