package com.sengami.gui_diary.view.list.element;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.sengami.domain_base.util.DateUtils;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.gui_base.view.list.element.ElementConverter;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class DiaryEntryListElementConverter implements ElementConverter<List<DiaryEntry>, List<DiaryEntryListElement>> {

    @NotNull
    @Override
    public List<DiaryEntryListElement> convert(@NotNull final List<DiaryEntry> diaryEntryList) {
        return Stream
            .of(diaryEntryList)
            .groupBy(DiaryEntry::getDate)
            .sorted(newestToOldestComparator())
            .map(this::flattenGroupedListInDateOrder)
            .reduce(this::combineAllGroups)
            .get();
    }

    private Comparator<Map.Entry<LocalDate, List<DiaryEntry>>> newestToOldestComparator() {
        return (o1, o2) -> o2.getKey().compareTo(o1.getKey());
    }

    @NotNull
    private List<DiaryEntryListElement> flattenGroupedListInDateOrder(@NotNull final Map.Entry<LocalDate, List<DiaryEntry>> dateGroup) {
        final List<DiaryEntryListElement> elements = new LinkedList<>();

        final List<DiaryEntryListElement> diaryEntryElements = Stream
            .of(dateGroup.getValue())
            .map(DiaryEntryListDiaryEntryElement::new)
            .collect(Collectors.toList());

        elements.add(new DiaryEntryListDateHeaderElement(DateUtils.format(dateGroup.getKey())));
        elements.addAll(diaryEntryElements);
        return elements;
    }

    @NotNull
    private List<DiaryEntryListElement> combineAllGroups(@NotNull final List<DiaryEntryListElement> accumulator,
                                                         @NotNull final List<DiaryEntryListElement> items) {
        accumulator.addAll(items);
        return accumulator;
    }
}