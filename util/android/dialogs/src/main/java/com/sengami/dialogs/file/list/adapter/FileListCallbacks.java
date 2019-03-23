package com.sengami.dialogs.file.list.adapter;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface FileListCallbacks {

    void onFileClicked(@NotNull final File file);

    void onDirectoryClicked(@NotNull final File file);
}