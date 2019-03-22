package com.sengami.gui_statistics.view.list.element;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.Map;

public final class StatisticsListChartElement extends StatisticsListElement {

    @NotNull
    private final Map<LocalDate, Integer> chartData;

    public StatisticsListChartElement(@NotNull final Map<LocalDate, Integer> chartData) {
        super(StatisticsListElementType.CHART);
        this.chartData = chartData;
    }

    @NotNull
    public Map<LocalDate, Integer> getChartData() {
        return chartData;
    }
}