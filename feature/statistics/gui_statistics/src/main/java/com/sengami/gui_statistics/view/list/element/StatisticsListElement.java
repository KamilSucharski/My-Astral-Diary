package com.sengami.gui_statistics.view.list.element;

import com.sengami.recycler_view_adapter.element.Element;

import org.jetbrains.annotations.NotNull;

public abstract class StatisticsListElement implements Element {

    @NotNull
    private final StatisticsListElementType type;

    public StatisticsListElement(@NotNull final StatisticsListElementType type) {
        this.type = type;
    }

    @Override
    @NotNull
    public StatisticsListElementType getType() {
        return type;
    }
}