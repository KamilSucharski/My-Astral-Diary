package com.sengami.domain_diary.operation;

import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.Operation;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public interface GetDiaryEntriesGroupedByDateOperation extends Operation<Map<LocalDate, List<DiaryEntry>>> {
}