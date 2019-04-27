package com.sengami.gui_statistics.view.list.element;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.Map;

public final class StatisticsListYearProgressElement extends StatisticsListElement {

    private final int year;
    @NotNull
    private final Map<LocalDate, Integer> highlightedDays;

    public StatisticsListYearProgressElement(final int year,
                                             @NotNull final Map<LocalDate, Integer> highlightedDays) {
        super(StatisticsListElementType.YEAR_PROGRESS);
        this.year = year;
        this.highlightedDays = highlightedDays;
    }

    public int getYear() {
        return year;
    }

    @NotNull
    public Map<LocalDate, Integer> getHighlightedDays() {
        return highlightedDays;
    }
}