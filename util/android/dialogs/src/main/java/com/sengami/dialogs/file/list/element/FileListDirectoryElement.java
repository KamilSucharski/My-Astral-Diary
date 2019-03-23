package com.sengami.dialogs.file.list.element;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class FileListDirectoryElement extends FileListElement {

    @NotNull
    private final File directory;
    private final boolean isGoBack;

    public FileListDirectoryElement(@NotNull final File directory,
                                    final boolean isGoBack) {
        super(FileListElementType.DIRECTORY);
        this.directory = directory;
        this.isGoBack = isGoBack;
    }

    @NotNull
    public File getDirectory() {
        return directory;
    }

    public boolean isGoBack() {
        return isGoBack;
    }
}