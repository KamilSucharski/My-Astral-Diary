package com.sengami.gui_diary.view.list.element;

import com.sengami.domain_diary.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;

public final class DiaryEntryListDiaryEntryElement extends DiaryEntryListElement {

    @NotNull
    private final DiaryEntry diaryEntry;

    public DiaryEntryListDiaryEntryElement(@NotNull final DiaryEntry diaryEntry) {
        super(DiaryEntryListElementType.DIARY_ENTRY);
        this.diaryEntry = diaryEntry;
    }

    @NotNull
    public DiaryEntry getDiaryEntry() {
        return diaryEntry;
    }
}
