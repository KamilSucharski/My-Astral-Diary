package com.sengami.domain_diary.operation;

import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.Operation;

import org.jetbrains.annotations.NotNull;

public interface DeleteDiaryEntryOperation extends Operation<Boolean> {

    @NotNull
    DeleteDiaryEntryOperation withDiaryEntry(@NotNull final DiaryEntry diaryEntry);
}