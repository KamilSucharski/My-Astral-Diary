package com.sengami.gui_main.view.list;

import com.sengami.gui_base.view.list.ElementType;
import com.sengami.gui_main.R;

import androidx.annotation.LayoutRes;

public enum MainViewPagerElementType implements ElementType {
    FRAGMENT(R.layout.element_main_view_pager_fragment);

    @LayoutRes
    private final int layoutRes;

    MainViewPagerElementType(@LayoutRes final int layoutRes) {
        this.layoutRes = layoutRes;
    }

    @Override
    @LayoutRes
    public int getLayoutRes() {
        return layoutRes;
    }
}