package com.sengami.gui_main.view.list;

import com.sengami.gui_base.view.list.Element;

import org.jetbrains.annotations.NotNull;

public abstract class MainViewPagerElement implements Element {

    @NotNull
    private final MainViewPagerElementType type;

    public MainViewPagerElement(@NotNull final MainViewPagerElementType type) {
        this.type = type;
    }

    @Override
    @NotNull
    public MainViewPagerElementType getType() {
        return type;
    }
}