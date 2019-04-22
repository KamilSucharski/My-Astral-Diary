package com.sengami.gui_statistics.view.list.converter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.sengami.domain_base.model.Statistics;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListEmptyStateElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListOtherStatisticsElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListYearProgressElement;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class StatisticsListElementConverter implements ElementConverter<Statistics, StatisticsListElement> {

    @NotNull
    @Override
    public List<StatisticsListElement> convert(@NotNull final Statistics statistics) {
        if (statistics.getTotalEntries() == 0) {
            return Collections.singletonList(new StatisticsListEmptyStateElement());
        }

        final List<StatisticsListElement> list = Stream
            .of(statistics.getEntryDatesGroupedByYears())
            .map(entry -> new StatisticsListYearProgressElement(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        list.add(new StatisticsListOtherStatisticsElement(statistics));

        return list;
    }
}