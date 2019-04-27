package com.sengami.gui_diary.di.module;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_diary.operation.CreateOrUpdateDiaryEntryOperation;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;
import com.sengami.domain_diary.operation.GetDiaryEntriesOperation;
import com.sengami.domain_diary.presenter.DiaryEntryComposerPresenter;
import com.sengami.domain_diary.presenter.DiaryEntryListPresenter;
import com.sengami.domain_diary.view.DiaryEntryComposerView;
import com.sengami.domain_diary.view.DiaryEntryListView;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    Presenter<DiaryEntryListView> diaryEntryListPresenter(@NotNull final GetDiaryEntriesOperation getDiaryEntriesOperation) {
        return new DiaryEntryListPresenter(getDiaryEntriesOperation);
    }

    @Provides
    @NotNull
    Presenter<DiaryEntryComposerView> diaryEntryComposerPresenter(@NotNull final CreateOrUpdateDiaryEntryOperation createOrUpdateDiaryEntryOperation,
                                                                  @NotNull final DeleteDiaryEntryOperation deleteDiaryEntryOperation) {
        return new DiaryEntryComposerPresenter(createOrUpdateDiaryEntryOperation, deleteDiaryEntryOperation);
    }
}