package com.sengami.domain_statistics.model;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.Map;

public class DiaryStatistics {

    @NotNull
    private final Map<LocalDate, Integer> entriesByDate;
    private final int totalEntries;
    private final int longestEntryCharacterCount;

    public DiaryStatistics(@NotNull final Map<LocalDate, Integer> entriesByDate,
                           final int totalEntries,
                           final int longestEntryCharacterCount) {
        this.entriesByDate = entriesByDate;
        this.totalEntries = totalEntries;
        this.longestEntryCharacterCount = longestEntryCharacterCount;
    }

    @NotNull
    public Map<LocalDate, Integer> getEntriesByDate() {
        return entriesByDate;
    }

    public int getTotalEntries() {
        return totalEntries;
    }

    public int getLongestEntryCharacterCount() {
        return longestEntryCharacterCount;
    }
}