package com.sengami.gui_diary.view.list.element;

import org.jetbrains.annotations.NotNull;

public final class DiaryEntryListDateHeaderElement extends DiaryEntryListElement {

    @NotNull
    private final String date;

    public DiaryEntryListDateHeaderElement(@NotNull final String date) {
        super(DiaryEntryListElementType.DATE_HEADER);
        this.date = date;
    }

    @NotNull
    public String getDate() {
        return date;
    }
}