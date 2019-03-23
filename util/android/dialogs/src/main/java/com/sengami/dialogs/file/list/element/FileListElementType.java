package com.sengami.dialogs.file.list.element;

import com.sengami.dialogs.R;
import com.sengami.recycler_view_adapter.element.ElementType;

import androidx.annotation.LayoutRes;

public enum FileListElementType implements ElementType {
    DIRECTORY(R.layout.dialog_file_picker_element_directory),
    FILE(R.layout.dialog_file_picker_element_file);

    @LayoutRes
    private final int layoutRes;

    FileListElementType(@LayoutRes final int layoutRes) {
        this.layoutRes = layoutRes;
    }

    @Override
    @LayoutRes
    public int getLayoutRes() {
        return layoutRes;
    }
}