package com.sengami.domain_base.operation.loading;

import org.jetbrains.annotations.NotNull;

public interface WithLoadingIndicator {

    @NotNull
    LoadingIndicator getLoadingIndicator();
}