package com.sengami.gui_diary.view.list.element;

import com.sengami.recycler_view_adapter.element.Element;

import org.jetbrains.annotations.NotNull;

public abstract class DiaryEntryListElement implements Element {

    @NotNull
    private final DiaryEntryListElementType type;

    protected DiaryEntryListElement(@NotNull final DiaryEntryListElementType type) {
        this.type = type;
    }

    @Override
    @NotNull
    public DiaryEntryListElementType getType() {
        return type;
    }
}