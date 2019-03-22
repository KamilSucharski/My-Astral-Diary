package com.sengami.domain_base.model;

public final class Statistics {

    private int totalEntries = 0;
    private int yearWithMostEntries = 0;
    private int longestEntryCharacterCount = 0;
    private double averageEntriesPerDay = 0D;

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