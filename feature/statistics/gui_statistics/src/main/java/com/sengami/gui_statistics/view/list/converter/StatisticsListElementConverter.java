package com.sengami.gui_statistics.view.list.converter;

import com.sengami.domain_base.model.Statistics;
import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.view.list.element.StatisticsListChartElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListTextWithNumberElement;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class StatisticsListElementConverter implements ElementConverter<Statistics, StatisticsListElement> {

    @NotNull
    @Override
    public List<StatisticsListElement> convert(@NotNull final Statistics statistics) {
        return Arrays.asList(
            new StatisticsListChartElement(statistics.getNumberOfEntriesByDate()),
            new StatisticsListTextWithNumberElement(R.string.statistic_total_entries, statistics.getTotalEntries()),
            new StatisticsListTextWithNumberElement(R.string.statistic_longest_entry_character_count, statistics.getLongestEntryCharacterCount())
        );
    }
}