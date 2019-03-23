package com.sengami.dialogs.file.list.element;

import com.sengami.recycler_view_adapter.element.Element;

import org.jetbrains.annotations.NotNull;

public abstract class FileListElement implements Element {

    @NotNull
    private final FileListElementType type;

    protected FileListElement(@NotNull final FileListElementType type) {
        this.type = type;
    }

    @Override
    @NotNull
    public FileListElementType getType() {
        return type;
    }
}