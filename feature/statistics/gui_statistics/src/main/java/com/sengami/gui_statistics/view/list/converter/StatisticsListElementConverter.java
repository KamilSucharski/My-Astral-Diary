package com.sengami.gui_statistics.view.list.converter;

import android.util.Pair;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.sengami.domain_base.model.Statistics;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListEmptyStateElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListOtherStatisticsElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListYearProgressElement;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class StatisticsListElementConverter implements ElementConverter<Statistics, StatisticsListElement> {

    @NotNull
    @Override
    public List<StatisticsListElement> convert(@NotNull final Statistics statistics) {
        if (statistics.getTotalEntries() == 0) {
            return Collections.singletonList(new StatisticsListEmptyStateElement());
        }

        final Map<Integer, Map<LocalDate, Integer>> yearsWithDatesWithEntryCount = Stream
            .of(statistics.getDaysWithEntryCount())
            .groupBy(dayWithEntryCount -> dayWithEntryCount.getKey().getYear())
            .sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey()))
            .map(yearWithDatesWithEntryCount -> {
                final Map<LocalDate, Integer> datesWithEntryCount = Stream
                    .of(yearWithDatesWithEntryCount.getValue())
                    .reduce(new LinkedHashMap<>(), ((accumulator, entry) -> {
                        accumulator.put(entry.getKey(), entry.getValue());
                        return accumulator;
                    }));

                return new Pair<>(yearWithDatesWithEntryCount.getKey(), datesWithEntryCount);
            })
            .reduce(new LinkedHashMap<>(), ((accumulator, entry) -> {
                accumulator.put(entry.first, entry.second);
                return accumulator;
            }));

        final List<StatisticsListElement> list = Stream
            .of(yearsWithDatesWithEntryCount)
            .map(entry -> new StatisticsListYearProgressElement(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        list.add(new StatisticsListOtherStatisticsElement(statistics));

        return list;
    }
}