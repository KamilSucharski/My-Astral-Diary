package com.sengami.gui_diary.di.module;

import com.sengami.domain_diary.contract.DiaryEntryListContract;
import com.sengami.domain_diary.operation.GetDiaryEntryListOperation;
import com.sengami.domain_diary.presenter.DiaryEntryListPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    DiaryEntryListContract.Presenter diaryEntryListContractPresenter(@NotNull final GetDiaryEntryListOperation getDiaryEntryListOperation) {
        return new DiaryEntryListPresenter(getDiaryEntryListOperation);
    }
}