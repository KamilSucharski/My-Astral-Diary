package com.sengami.gui_diary.di.module;

import com.sengami.domain_diary.operation.CreateOrUpdateDiaryEntryOperation;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;
import com.sengami.domain_diary.operation.GetDiaryEntryListOperation;
import com.sengami.domain_diary.presenter.DiaryEntryComposerPresenter;
import com.sengami.domain_diary.presenter.DiaryEntryListPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    DiaryEntryListPresenter diaryEntryListPresenter(@NotNull final GetDiaryEntryListOperation getDiaryEntryListOperation) {
        return new DiaryEntryListPresenter(getDiaryEntryListOperation);
    }

    @Provides
    @NotNull
    DiaryEntryComposerPresenter diaryEntryComposerPresenter(@NotNull final CreateOrUpdateDiaryEntryOperation createOrUpdateDiaryEntryOperation,
                                                                         @NotNull final DeleteDiaryEntryOperation deleteDiaryEntryOperation) {
        return new DiaryEntryComposerPresenter(createOrUpdateDiaryEntryOperation, deleteDiaryEntryOperation);
    }
}