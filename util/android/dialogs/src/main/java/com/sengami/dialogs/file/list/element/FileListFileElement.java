package com.sengami.dialogs.file.list.element;

import com.sengami.dialogs.file.list.adapter.FileListCallbacks;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class FileListFileElement extends FileListElement {

    @NotNull
    private final File file;
    @NotNull
    private final FileListCallbacks callbacks;

    public FileListFileElement(@NotNull final File file,
                               @NotNull final FileListCallbacks callbacks) {
        super(FileListElementType.FILE);
        this.file = file;
        this.callbacks = callbacks;
    }

    @NotNull
    public File getFile() {
        return file;
    }

    @NotNull
    public FileListCallbacks getCallbacks() {
        return callbacks;
    }
}