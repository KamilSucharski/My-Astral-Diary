package com.sengami.domain_diary.view;

import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_diary.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;

public interface DiaryEntryListView extends WithErrorHandler, WithLoadingIndicator {

    @NotNull
    Observable<Boolean> getRefreshListTrigger();

    @NotNull
    Observable<DiaryEntry> getDiaryEntryClickedTrigger();

    @NotNull
    Observable<Boolean> getAddNewDiaryClickedEntryTrigger();

    void showDiaryEntryList(@NotNull final List<DiaryEntry> diaryEntryList);

    void navigateToDiaryEntryComposerScreen(@NotNull final DiaryEntry diaryEntry);

}