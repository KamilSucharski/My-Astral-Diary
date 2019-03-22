package com.sengami.data_base.mapper;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.domain_base.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

public final class DiaryEntryMapper implements Mapper<DiaryEntryDBO, DiaryEntry> {

    @Override
    public DiaryEntryDBO toDBO(@NotNull final DiaryEntry source) {
        final DiaryEntryDBO result = new DiaryEntryDBO();
        result.setId(source.getId());
        result.setDate(source.getDate().toDate());
        result.setTitle(source.getTitle());
        result.setBody(source.getBody());
        return result;
    }

    @Override
    public DiaryEntry toModel(@NotNull final DiaryEntryDBO source) {
        final DiaryEntry result = new DiaryEntry();
        result.setId(source.getId());
        result.setDate(LocalDate.fromDateFields(source.getDate()));
        result.setTitle(source.getTitle());
        result.setBody(source.getBody());
        return result;
    }
}