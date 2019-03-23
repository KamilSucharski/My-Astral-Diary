package com.sengami.dialogs.file.list.element;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class FileListDirectoryElement extends FileListElement {

    @NotNull
    private final File directory;

    public FileListDirectoryElement(@NotNull final File directory) {
        super(FileListElementType.DIRECTORY);
        this.directory = directory;
    }

    @NotNull
    public File getDirectory() {
        return directory;
    }
}