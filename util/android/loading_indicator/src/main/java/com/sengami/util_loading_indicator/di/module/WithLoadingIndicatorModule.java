package com.sengami.util_loading_indicator.di.module;

import com.sengami.domain_base.loading.WithLoadingIndicator;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class WithLoadingIndicatorModule {

    @NotNull
    private final WithLoadingIndicator withLoadingIndicator;

    public WithLoadingIndicatorModule(@NotNull final WithLoadingIndicator withLoadingIndicator) {
        this.withLoadingIndicator = withLoadingIndicator;
    }

    @Provides
    @NotNull
    WithLoadingIndicator withLoadingIndicator() {
        return withLoadingIndicator;
    }
}