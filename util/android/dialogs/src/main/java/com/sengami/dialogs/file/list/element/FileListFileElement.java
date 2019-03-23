package com.sengami.dialogs.file.list.element;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class FileListFileElement extends FileListElement {

    @NotNull
    private final File file;
    private boolean isSelected;

    public FileListFileElement(@NotNull final File file) {
        super(FileListElementType.FILE);
        this.file = file;
    }

    @NotNull
    public File getFile() {
        return file;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(final boolean selected) {
        isSelected = selected;
    }
}