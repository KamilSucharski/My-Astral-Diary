package com.sengami.gui_statistics.view.list.element;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.Collection;

public final class StatisticsListYearProgressElement extends StatisticsListElement {

    private final int year;
    @NotNull
    private final Collection<LocalDate> highlightedDays;

    public StatisticsListYearProgressElement(final int year,
                                             @NotNull final Collection<LocalDate> highlightedDays) {
        super(StatisticsListElementType.YEAR_PROGRESS);
        this.year = year;
        this.highlightedDays = highlightedDays;
    }

    public int getYear() {
        return year;
    }

    @NotNull
    public Collection<LocalDate> getHighlightedDays() {
        return highlightedDays;
    }
}