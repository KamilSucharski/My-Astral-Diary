package com.sengami.data_diary.mapper;

import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_diary.dbo.DiaryEntryDBO;
import com.sengami.domain_diary.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;

public class DiaryEntryMapper implements Mapper<DiaryEntryDBO, DiaryEntry> {

    @Override
    public DiaryEntryDBO toDBO(@NotNull final DiaryEntry source) {
        final DiaryEntryDBO result = new DiaryEntryDBO();
        result.setId(source.getId());
        result.setDate(source.getDate());
        result.setTitle(source.getTitle());
        result.setBody(source.getBody());
        return result;
    }

    @Override
    public DiaryEntry toModel(@NotNull final DiaryEntryDBO source) {
        final DiaryEntry result = new DiaryEntry();
        result.setId(source.getId());
        result.setDate(source.getDate());
        result.setTitle(source.getTitle());
        result.setBody(source.getBody());
        return result;
    }
}