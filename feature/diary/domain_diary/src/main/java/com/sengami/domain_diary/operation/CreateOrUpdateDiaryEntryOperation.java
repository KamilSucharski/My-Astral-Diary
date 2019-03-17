package com.sengami.domain_diary.operation;

import com.sengami.domain_base.operation.Operation;
import com.sengami.domain_diary.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;

public interface CreateOrUpdateDiaryEntryOperation extends Operation<Boolean> {

    @NotNull
    CreateOrUpdateDiaryEntryOperation withDiaryEntry(@NotNull final DiaryEntry diaryEntry);
}