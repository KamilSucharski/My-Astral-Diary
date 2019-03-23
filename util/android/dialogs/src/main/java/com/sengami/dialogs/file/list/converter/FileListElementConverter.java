package com.sengami.dialogs.file.list.converter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.sengami.dialogs.file.list.adapter.FileListCallbacks;
import com.sengami.dialogs.file.list.element.FileListDirectoryElement;
import com.sengami.dialogs.file.list.element.FileListElement;
import com.sengami.dialogs.file.list.element.FileListFileElement;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public final class FileListElementConverter implements ElementConverter<List<File>, FileListElement> {

    @NotNull
    private final FileListCallbacks callbacks;

    public FileListElementConverter(@NotNull final FileListCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @NotNull
    @Override
    public List<FileListElement> convert(@NotNull final List<File> fileList) {
        return Stream.of(fileList)
            .map(file -> {
                if (file.isDirectory()) {
                    return new FileListDirectoryElement(file, callbacks);
                } else {
                    return new FileListFileElement(file, callbacks);
                }
            })
            .collect(Collectors.toList());
    }
}