package com.sengami.android_storage;

import android.content.Context;

import com.sengami.data_base.util.InternalStoragePathProvider;

import org.jetbrains.annotations.NotNull;

public class AndroidInternalStoragePathProvider implements InternalStoragePathProvider {

    @NotNull
    private static final String INTERNAL_STORAGE_PATH_FORMAT = "data/data/%1$s/";

    @NotNull
    private final Context context;

    @NotNull
    public AndroidInternalStoragePathProvider(@NotNull final Context context) {
        this.context = context;
    }

    @Override
    @NotNull
    public String provide() {
        return String.format(INTERNAL_STORAGE_PATH_FORMAT, context.getPackageName());
    }
}