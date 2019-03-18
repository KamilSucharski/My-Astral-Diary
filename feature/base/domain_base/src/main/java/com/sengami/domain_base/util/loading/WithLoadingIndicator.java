package com.sengami.domain_base.util.loading;

import org.jetbrains.annotations.NotNull;

public interface WithLoadingIndicator {

    @NotNull
    LoadingIndicator getLoadingIndicator();
}