package com.sengami.android_storage;

import android.os.Environment;

import com.sengami.data_base.util.ExternalStoragePathProvider;

import org.jetbrains.annotations.NotNull;

public class AndroidExternalStoragePathProvider implements ExternalStoragePathProvider {

    @Override
    @NotNull
    public String provide() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}