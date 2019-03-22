package com.sengami.domain_diary.view;

import com.sengami.domain_base.error.WithErrorHandler;
import com.sengami.domain_base.loading.WithLoadingIndicator;
import com.sengami.domain_base.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public interface DiaryEntryListView extends WithErrorHandler, WithLoadingIndicator {

    @NotNull
    Observable<Boolean> getRefreshListTrigger();

    @NotNull
    Observable<DiaryEntry> getDiaryEntryClickedTrigger();

    @NotNull
    Observable<Boolean> getAddNewDiaryClickedEntryTrigger();

    void showDiaryEntriesGroupedByDate(@NotNull final Map<LocalDate, List<DiaryEntry>> diaryEntriesGroupedByDate);

    void navigateToDiaryEntryComposerScreen(@NotNull final DiaryEntry diaryEntry);

}