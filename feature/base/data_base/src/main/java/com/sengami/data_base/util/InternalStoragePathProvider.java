package com.sengami.data_base.util;

import org.jetbrains.annotations.NotNull;

public interface InternalStoragePathProvider {

    @NotNull
    String provide();
}