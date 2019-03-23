package com.sengami.dialogs.file.list.binder;

import android.content.Context;

import com.sengami.dialogs.R;
import com.sengami.dialogs.databinding.DialogFilePickerElementDirectoryBinding;
import com.sengami.dialogs.file.list.adapter.FileListCallbacks;
import com.sengami.dialogs.file.list.element.FileListDirectoryElement;
import com.sengami.dialogs.file.list.element.FileListElement;
import com.sengami.dialogs.file.list.element.FileListElementType;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import static com.sengami.clicks.Clicks.onClick;

public final class FileListDirectoryElementBinder extends ViewHolderBinder<FileListElement, FileListDirectoryElement, DialogFilePickerElementDirectoryBinding> {

    @NotNull
    private final Context context;
    @NotNull
    private final FileListCallbacks callbacks;

    public FileListDirectoryElementBinder(@NotNull final Context context,
                                          @NotNull final FileListCallbacks callbacks) {
        this.context = context;
        this.callbacks = callbacks;
    }

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return FileListElementType.DIRECTORY;
    }

    @Override
    protected final void performBind(@NotNull final DialogFilePickerElementDirectoryBinding binding,
                                     @NotNull final FileListDirectoryElement item) {
        final File directory = item.getDirectory();
        binding.setName(item.isGoBack() ? context.getString(R.string.file_picker_dialog_directory_parent) :directory.getName());
        onClick(binding, () -> callbacks.onDirectoryClicked(directory));
    }
}