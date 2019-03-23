package com.sengami.recycler_view_adapter.element;

import androidx.annotation.LayoutRes;

public interface ElementType {

    @LayoutRes
    int getLayoutRes();

    int ordinal();
}