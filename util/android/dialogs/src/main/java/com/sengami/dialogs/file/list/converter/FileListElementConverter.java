package com.sengami.dialogs.file.list.converter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.sengami.dialogs.file.list.element.FileListDirectoryElement;
import com.sengami.dialogs.file.list.element.FileListElement;
import com.sengami.dialogs.file.list.element.FileListFileElement;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public final class FileListElementConverter implements ElementConverter<List<File>, FileListElement> {

    @NotNull
    @Override
    public List<FileListElement> convert(@NotNull final List<File> fileList) {
        return Stream.of(fileList)
            .map(file -> {
                if (file.isDirectory()) {
                    return new FileListDirectoryElement(file);
                } else {
                    return new FileListFileElement(file);
                }
            })
            .collect(Collectors.toList());
    }
}