package com.sengami.domain_base.model;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

public final class Statistics {

    @NotNull
    private Map<LocalDate, Integer> numberOfEntriesByDate = new HashMap<>();
    private int totalEntries = 0;
    private int longestEntryCharacterCount = 0;

    @NotNull
    public Map<LocalDate, Integer> getNumberOfEntriesByDate() {
        return numberOfEntriesByDate;
    }

    public void setNumberOfEntriesByDate(@NotNull final Map<LocalDate, Integer> numberOfEntriesByDate) {
        this.numberOfEntriesByDate = numberOfEntriesByDate;
    }

    public int getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(int totalEntries) {
        this.totalEntries = totalEntries;
    }

    public int getLongestEntryCharacterCount() {
        return longestEntryCharacterCount;
    }

    public void setLongestEntryCharacterCount(int longestEntryCharacterCount) {
        this.longestEntryCharacterCount = longestEntryCharacterCount;
    }
}