package com.sengami.data_base.mapper;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.domain_base.model.DiaryEntry;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiaryEntryMapperTest {

    private final DiaryEntryMapper diaryEntryMapper = new DiaryEntryMapper();

    private DiaryEntry expectedDiaryEntry = new DiaryEntry();
    private DiaryEntryDBO expectedDiaryEntryDBO = new DiaryEntryDBO();

    @Before
    public void before() {
        final int id = 1;
        final String title = "Title";
        final String body = "Body";
        final LocalDate date = LocalDate.now();

        expectedDiaryEntry.setId(id);
        expectedDiaryEntry.setTitle(title);
        expectedDiaryEntry.setBody(body);
        expectedDiaryEntry.setDate(date);

        expectedDiaryEntryDBO.setId(id);
        expectedDiaryEntryDBO.setTitle(title);
        expectedDiaryEntryDBO.setBody(body);
        expectedDiaryEntryDBO.setDate(date.toDate());
    }

    @Test
    public void correctlyMappingToModel() {
        final DiaryEntry result = diaryEntryMapper.toModel(expectedDiaryEntryDBO);
        assertEquals(expectedDiaryEntry.getId(), result.getId());
        assertEquals(expectedDiaryEntry.getTitle(), result.getTitle());
        assertEquals(expectedDiaryEntry.getBody(), result.getBody());
        assertEquals(expectedDiaryEntry.getDate(), result.getDate());
    }

    @Test
    public void correctlyMappingToDBO() {
        final DiaryEntryDBO result = diaryEntryMapper.toDBO(expectedDiaryEntry);
        assertEquals(expectedDiaryEntryDBO.getId(), result.getId());
        assertEquals(expectedDiaryEntryDBO.getTitle(), result.getTitle());
        assertEquals(expectedDiaryEntryDBO.getBody(), result.getBody());
        assertEquals(expectedDiaryEntryDBO.getDate(), result.getDate());
    }
}