package com.sengami.gui_diary.di.module;

import com.sengami.data_base.mapper.Mapper;
import com.sengami.data_diary.dbo.DiaryEntryDBO;
import com.sengami.data_diary.mapper.DiaryEntryMapper;
import com.sengami.domain_diary.contract.DiaryEntryListContract;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.domain_diary.operation.GetDiaryEntryListOperation;
import com.sengami.domain_diary.presenter.DiaryEntryListPresenter;

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