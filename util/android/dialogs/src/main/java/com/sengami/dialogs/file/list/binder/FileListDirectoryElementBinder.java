package com.sengami.dialogs.file.list.binder;

import com.sengami.dialogs.databinding.DialogFilePickerElementDirectoryBinding;
import com.sengami.dialogs.file.list.element.FileListDirectoryElement;
import com.sengami.dialogs.file.list.element.FileListElement;
import com.sengami.dialogs.file.list.element.FileListElementType;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import static com.sengami.clicks.Clicks.onClick;

public final class FileListDirectoryElementBinder extends ViewHolderBinder<FileListElement, FileListDirectoryElement, DialogFilePickerElementDirectoryBinding> {

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return FileListElementType.DIRECTORY;
    }

    @Override
    protected final void performBind(@NotNull final DialogFilePickerElementDirectoryBinding binding,
                                     @NotNull final FileListDirectoryElement item) {
        final File directory = item.getDirectory();
        binding.setName(directory.getName());
        onClick(binding, () -> item.getCallbacks().onDirectoryClicked(directory));
    }
}