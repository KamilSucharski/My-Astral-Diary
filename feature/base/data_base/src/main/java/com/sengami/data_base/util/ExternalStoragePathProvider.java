package com.sengami.data_base.util;

import org.jetbrains.annotations.NotNull;

public interface ExternalStoragePathProvider {

    @NotNull
    String provide();
}