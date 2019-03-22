package com.sengami.gui_settings.di.module;

import com.sengami.data_base.dbo.DiaryEntryDBO;
import com.sengami.data_base.mapper.DiaryEntryMapper;
import com.sengami.data_base.mapper.Mapper;
import com.sengami.domain_base.model.DiaryEntry;

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