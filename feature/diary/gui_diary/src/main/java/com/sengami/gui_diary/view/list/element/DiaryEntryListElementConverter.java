package com.sengami.gui_diary.view.list.element;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.gui_base.view.list.element.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

public final class DiaryEntryListElementConverter implements ElementConverter<List<DiaryEntry>, List<DiaryEntryListElement>> {

    @NotNull
    @Override
    public List<DiaryEntryListElement> convert(@NotNull final List<DiaryEntry> diaryEntryList) {
        final List<DiaryEntryListElement> items = Stream.of(diaryEntryList)
            .map(DiaryEntryListDiaryEntryElement::new)
            .collect(Collectors.toList());

        items.add(new DiaryEntryListDateHeaderElement(new Date()));
        return items;
    }
}
