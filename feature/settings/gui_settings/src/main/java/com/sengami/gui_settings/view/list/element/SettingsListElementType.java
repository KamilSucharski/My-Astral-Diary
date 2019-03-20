package com.sengami.gui_settings.view.list.element;

import com.sengami.gui_settings.R;
import com.sengami.recycler_view_adapter.element.ElementType;

import androidx.annotation.LayoutRes;

public enum SettingsListElementType implements ElementType {
    SECTION(R.layout.element_section),
    ITEM(R.layout.element_item);

    @LayoutRes
    private final int layoutRes;

    SettingsListElementType(@LayoutRes final int layoutRes) {
        this.layoutRes = layoutRes;
    }

    @Override
    @LayoutRes
    public int getLayoutRes() {
        return layoutRes;
    }
}