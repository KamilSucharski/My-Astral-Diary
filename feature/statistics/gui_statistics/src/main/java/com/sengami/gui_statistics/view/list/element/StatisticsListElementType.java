package com.sengami.gui_statistics.view.list.element;

import androidx.annotation.LayoutRes;

import com.sengami.gui_statistics.R;
import com.sengami.recycler_view_adapter.element.ElementType;

public enum StatisticsListElementType implements ElementType {
    YEAR_PROGRESS(R.layout.element_year_progress),
    TEXT_WITH_NUMBER(R.layout.element_text_with_number),
    EMPTY_STATE(R.layout.element_statistics_empty_state);

    @LayoutRes
    private final int layoutRes;

    StatisticsListElementType(@LayoutRes final int layoutRes) {
        this.layoutRes = layoutRes;
    }

    @Override
    @LayoutRes
    public int getLayoutRes() {
        return layoutRes;
    }
}