package com.sengami.dialogs.file.list.element;

import com.sengami.dialogs.file.list.adapter.FileListCallbacks;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class FileListDirectoryElement extends FileListElement {

    @NotNull
    private final File directory;
    @NotNull
    private final FileListCallbacks callbacks;

    public FileListDirectoryElement(@NotNull final File directory,
                                    @NotNull final FileListCallbacks callbacks) {
        super(FileListElementType.DIRECTORY);
        this.directory = directory;
        this.callbacks = callbacks;
    }

    @NotNull
    public File getDirectory() {
        return directory;
    }

    @NotNull
    public FileListCallbacks getCallbacks() {
        return callbacks;
    }
}