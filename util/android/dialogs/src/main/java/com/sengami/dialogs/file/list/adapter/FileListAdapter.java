package com.sengami.dialogs.file.list.adapter;

import android.content.Context;

import com.sengami.dialogs.file.list.binder.FileListDirectoryElementBinder;
import com.sengami.dialogs.file.list.binder.FileListFileElementBinder;
import com.sengami.dialogs.file.list.element.FileListElement;
import com.sengami.dialogs.file.list.element.FileListElementType;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class FileListAdapter extends BaseAdapter<FileListElement, FileListElementType> {

    public FileListAdapter(@NotNull final Context context,
                           @NotNull final FileListCallbacks callbacks) {
        super(
            FileListElementType.values(),
            Arrays.asList(
                new FileListDirectoryElementBinder(context, callbacks),
                new FileListFileElementBinder(callbacks)
            )
        );
    }
}