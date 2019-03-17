package com.sengami.gui_diary.view.list.element;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public final class DiaryEntryListDateHeaderElement extends DiaryEntryListElement {

    @NotNull
    private final Date date;

    public DiaryEntryListDateHeaderElement(@NotNull final Date date) {
        super(DiaryEntryListElementType.DATE_HEADER);
        this.date = date;
    }

    @NotNull
    public Date getDate() {
        return date;
    }
}