package com.sengami.domain_diary.view;

import com.sengami.domain_base.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import io.reactivex.Observable;

public interface DiaryEntryComposerView {

    @NotNull
    Observable<DiaryEntry> getSaveDiaryEntryTrigger();

    @NotNull
    Observable<DiaryEntry> getDeleteDiaryEntryTrigger();

    @NotNull
    Observable<LocalDate> getDateChangedTrigger();

    @NotNull
    Observable<Boolean> getReturnTrigger();

    void changeDate(@NotNull final LocalDate localDate);

    void showOperationSuccessMessage();

    void navigateBack();
}