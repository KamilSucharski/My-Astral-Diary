package com.sengami.dialogs.file.list.binder;

import com.sengami.dialogs.databinding.DialogFilePickerElementFileBinding;
import com.sengami.dialogs.file.list.element.FileListElement;
import com.sengami.dialogs.file.list.element.FileListElementType;
import com.sengami.dialogs.file.list.element.FileListFileElement;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import static com.sengami.clicks.Clicks.onClick;

public final class FileListFileElementBinder extends ViewHolderBinder<FileListElement, FileListFileElement, DialogFilePickerElementFileBinding> {

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return FileListElementType.FILE;
    }

    @Override
    protected final void performBind(@NotNull final DialogFilePickerElementFileBinding binding,
                                     @NotNull final FileListFileElement item) {
        final File file = item.getFile();
        binding.setName(file.getName());
        onClick(binding, () -> item.getCallbacks().onFileClicked(file));
    }
}