package com.sengami.gui_base.view.list;

import androidx.annotation.LayoutRes;

public interface ElementType {

    @LayoutRes
    int getLayoutRes();

    int ordinal();
}
