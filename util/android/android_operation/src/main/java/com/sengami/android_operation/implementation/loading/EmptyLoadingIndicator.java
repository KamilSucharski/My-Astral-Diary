package com.sengami.android_operation.implementation.loading;

import com.sengami.domain_base.operation.configuration.loading.LoadingIndicator;

public final class EmptyLoadingIndicator implements LoadingIndicator {

    @Override
    public void setLoading(final boolean loading) {
        //no-op
    }
}