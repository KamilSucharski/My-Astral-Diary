package com.sengami.domain_base.operation;

import io.reactivex.Observable;

public interface Operation<T> {

    Observable<T> execute();
}