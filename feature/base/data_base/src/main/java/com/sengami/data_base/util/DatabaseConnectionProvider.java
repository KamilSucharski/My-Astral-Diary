package com.sengami.data_base.util;

import com.j256.ormlite.support.ConnectionSource;

import org.jetbrains.annotations.NotNull;

public interface DatabaseConnectionProvider {

    @NotNull
    ConnectionSource provide();
}