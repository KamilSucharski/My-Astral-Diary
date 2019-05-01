package com.sengami.gui_diary.view.list.converter;

import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.gui_diary.view.list.element.DiaryEntryListDateHeaderElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListDiaryEntryElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListEmptyStateElement;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DiaryEntryListElementConverterTest {

    private DiaryEntryListElementConverter converter = new DiaryEntryListElementConverter();

    @Test
    public void showingEmptyStateInCaseOfNoDiaryEntries() {
        final List<DiaryEntry> entries = Collections.emptyList();

        final List<DiaryEntryListElement> result = converter.convert(entries);

        assertEquals(1, result.size());
        assert (result.get(0) instanceof DiaryEntryListEmptyStateElement);
    }

    @Test
    public void entriesGetGroupedByDate() {
        final List<DiaryEntry> entries = new ArrayList<>();

        final DiaryEntry entry1 = new DiaryEntry();
        entry1.setDate(LocalDate.now());
        entries.add(entry1);
        final DiaryEntry entry2 = new DiaryEntry();
        entry2.setDate(LocalDate.now());
        entries.add(entry2);

        final List<DiaryEntryListElement> result = converter.convert(entries);

        assertEquals(3, result.size());
        assert (result.get(0) instanceof DiaryEntryListDateHeaderElement);
        assert (result.get(1) instanceof DiaryEntryListDiaryEntryElement);
        assert (result.get(2) instanceof DiaryEntryListDiaryEntryElement);
    }
}