package com.sengami.domain_base.model;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public final class Statistics {

    private Map<Integer, List<LocalDate>> entryDatesGroupedByYears;
    private int totalEntries = 0;
    private int yearWithMostEntries = 0;
    private int longestEntryCharacterCount = 0;
    private double averageEntriesPerDay = 0D;

    public Map<Integer, List<LocalDate>> getEntryDatesGroupedByYears() {
        return entryDatesGroupedByYears;
    }

    public void setEntryDatesGroupedByYears(Map<Integer, List<LocalDate>> entryDatesGroupedByYears) {
        this.entryDatesGroupedByYears = entryDatesGroupedByYears;
    }

    public int getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(int totalEntries) {
        this.totalEntries = totalEntries;
    }

    public int getYearWithMostEntries() {
        return yearWithMostEntries;
    }

    public void setYearWithMostEntries(int yearWithMostEntries) {
        this.yearWithMostEntries = yearWithMostEntries;
    }

    public int getLongestEntryCharacterCount() {
        return longestEntryCharacterCount;
    }

    public void setLongestEntryCharacterCount(int longestEntryCharacterCount) {
        this.longestEntryCharacterCount = longestEntryCharacterCount;
    }

    public double getAverageEntriesPerDay() {
        return averageEntriesPerDay;
    }

    public void setAverageEntriesPerDay(double averageEntriesPerDay) {
        this.averageEntriesPerDay = averageEntriesPerDay;
    }
}