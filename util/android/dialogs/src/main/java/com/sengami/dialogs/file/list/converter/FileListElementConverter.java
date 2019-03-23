package com.sengami.dialogs.file.list.converter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.sengami.dialogs.file.FilePickerDialog;
import com.sengami.dialogs.file.list.element.FileListDirectoryElement;
import com.sengami.dialogs.file.list.element.FileListElement;
import com.sengami.dialogs.file.list.element.FileListFileElement;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public final class FileListElementConverter implements ElementConverter<File, FileListElement> {

    @NotNull
    @Override
    public List<FileListElement> convert(@NotNull final File file) {
        final List<FileListElement> elements = Stream.of(file.listFiles())
            .map(childFile -> {
                if (childFile.isDirectory()) {
                    return new FileListDirectoryElement(childFile, false);
                } else {
                    return new FileListFileElement(childFile);
                }
            })
            .collect(Collectors.toList());

        if (!file.equals(FilePickerDialog.ROOT)) {
            elements.add(0, new FileListDirectoryElement(file.getParentFile(), true));
        }

        return elements;
    }
}