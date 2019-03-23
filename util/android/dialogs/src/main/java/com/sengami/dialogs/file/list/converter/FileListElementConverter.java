package com.sengami.dialogs.file.list.converter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
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
    private final String[] allowedExtensions;

    public FileListElementConverter(@NotNull final String... allowedExtensions) {
        this.allowedExtensions = allowedExtensions;
    }

    @NotNull
    @Override
    public List<FileListElement> convert(@NotNull final File file) {
        final List<FileListElement> elements = Stream.of(file.listFiles())
            .filter(filterFilesByAllowedExtensions())
            .map(mapToDirectoryOrFile())
            .collect(Collectors.toList());

        if (!file.equals(FilePickerDialog.ROOT)) {
            elements.add(0, new FileListDirectoryElement(file.getParentFile(), true));
        }

        return elements;
    }

    @NotNull
    private Predicate<File> filterFilesByAllowedExtensions() {
        return file -> {
            if (file.isDirectory()) {
                return true;
            } else {
                boolean shouldAddFileToList = allowedExtensions.length == 0;
                for (final String extension : allowedExtensions) {
                    if (file.getName().endsWith(extension)) {
                        shouldAddFileToList = true;
                    }
                }
                return shouldAddFileToList;
            }
        };
    }

    @NotNull
    private Function<File, FileListElement> mapToDirectoryOrFile() {
        return file -> {
            if (file.isDirectory()) {
                return new FileListDirectoryElement(file, false);
            } else {
                return new FileListFileElement(file);
            }
        };
    }
}