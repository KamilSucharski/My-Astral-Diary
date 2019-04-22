package com.sengami.gui_statistics.view.list.element;

import com.sengami.domain_base.model.Statistics;

import org.jetbrains.annotations.NotNull;

public final class StatisticsListOtherStatisticsElement extends StatisticsListElement {

    @NotNull
    private final Statistics statistics;

    public StatisticsListOtherStatisticsElement(@NotNull final Statistics statistics) {
        super(StatisticsListElementType.OTHER_STATISTICS);
        this.statistics = statistics;
    }

    @NotNull
    public Statistics getStatistics() {
        return statistics;
    }
}