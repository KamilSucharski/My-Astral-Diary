package com.sengami.domain_diary.operation;

import com.sengami.domain_base.operation.Operation;
import com.sengami.domain_diary.model.DiaryEntry;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public interface GetDiaryEntryListGroupedByDateOperation extends Operation<Map<LocalDate, List<DiaryEntry>>> {
}