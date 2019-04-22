package com.sengami.gui_statistics.view.list.converter;

import com.annimon.stream.Stream;
import com.sengami.domain_base.model.Statistics;
import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListEmptyStateElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListTextWithNumberElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListYearProgressElement;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class StatisticsListElementConverter implements ElementConverter<Statistics, StatisticsListElement> {

    @NotNull
    @Override
    public List<StatisticsListElement> convert(@NotNull final Statistics statistics) {
        if (statistics.getTotalEntries() == 0) {
            return Collections.singletonList(new StatisticsListEmptyStateElement());
        }

        final List<StatisticsListYearProgressElement> yearProgressElements = Stream
            .of(statistics.getEntryDatesGroupedByYears())
            .map(entry -> new StatisticsListYearProgressElement(entry.getKey(), entry.getValue()))
            .toList();

        final List<StatisticsListTextWithNumberElement> textWithNumbersStatisticsElements = Arrays.asList(
            new StatisticsListTextWithNumberElement(R.string.statistic_total_entries, String.valueOf(statistics.getTotalEntries())),
            new StatisticsListTextWithNumberElement(R.string.statistic_year_with_most_entries, String.valueOf(statistics.getYearWithMostEntries())),
            new StatisticsListTextWithNumberElement(R.string.statistic_longest_entry_character_count, String.valueOf(statistics.getLongestEntryCharacterCount())),
            new StatisticsListTextWithNumberElement(R.string.statistic_average_entries_per_day, String.valueOf(statistics.getAverageEntriesPerDay()))
        );

        final List<StatisticsListElement> results = new ArrayList<>();
        results.addAll(yearProgressElements);
        results.addAll(textWithNumbersStatisticsElements);
        return results;
    }
}