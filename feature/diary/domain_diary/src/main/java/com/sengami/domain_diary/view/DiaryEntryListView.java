package com.sengami.domain_diary.view;

import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.error.WithErrorHandler;
import com.sengami.domain_base.operation.loading.WithLoadingIndicator;

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

    void showDiaryEntries(@NotNull final List<DiaryEntry> diaryEntries);

    void navigateToDiaryEntryComposerScreen(@NotNull final DiaryEntry diaryEntry);
}