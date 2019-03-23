package com.sengami.android_operation.implementation;

import com.sengami.domain_base.operation.loading.LoadingIndicator;

public final class EmptyLoadingIndicator implements LoadingIndicator {

    @Override
    public void setLoading(final boolean loading) {
        //no-op
    }
}