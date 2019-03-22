package com.sengami.gui_statistics.di.module;

import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_diary.dbo.DiaryEntryDBO;
import com.sengami.data_diary.mapper.DiaryEntryMapper;
import com.sengami.domain_diary.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class MapperModule {

    @Provides
    @NotNull
    Mapper<DiaryEntryDBO, DiaryEntry> diaryEntryMapper() {
        return new DiaryEntryMapper();
    }
}