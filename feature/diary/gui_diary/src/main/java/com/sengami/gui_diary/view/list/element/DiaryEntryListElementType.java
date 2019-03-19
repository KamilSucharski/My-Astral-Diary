package com.sengami.gui_diary.view.list.element;

import com.sengami.gui_diary.R;
import com.sengami.recycler_view_adapter.element.ElementType;

import androidx.annotation.LayoutRes;

public enum DiaryEntryListElementType implements ElementType {
    DIARY_ENTRY(R.layout.element_diary_entry),
    DATE_HEADER(R.layout.element_date_header),
    EMPTY_STATE(R.layout.element_empty_state);

    @LayoutRes
    private final int layoutRes;

    DiaryEntryListElementType(@LayoutRes final int layoutRes) {
        this.layoutRes = layoutRes;
    }

    @Override
    @LayoutRes
    public int getLayoutRes() {
        return layoutRes;
    }
}