package com.sengami.gui_statistics.view.list.converter;

import com.sengami.domain_base.model.Statistics;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListEmptyStateElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListOtherStatisticsElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListYearProgressElement;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StatisticsListElementConverterTest {

    private StatisticsListElementConverter converter = new StatisticsListElementConverter();

    @Test
    public void returningEmptyStateInCaseOfNoDiaryEntries() {
        final Statistics statistics = new Statistics();
        statistics.setTotalEntries(0);

        final List<StatisticsListElement> result = converter.convert(statistics);

        assertEquals(1, result.size());
        assert (result.get(0) instanceof StatisticsListEmptyStateElement);
    }

    @Test
    public void returningYearProgressAndOtherStatistics() {
        final Statistics statistics = new Statistics();
        final Map<LocalDate, Integer> daysWithEntryCount = new HashMap<>();
        daysWithEntryCount.put(LocalDate.now(), 1);
        statistics.setTotalEntries(1);
        statistics.setDaysWithEntryCount(daysWithEntryCount);

        final List<StatisticsListElement> result = converter.convert(statistics);

        assertEquals(2, result.size());
        assert (result.get(0) instanceof StatisticsListYearProgressElement);
        assert (result.get(1) instanceof StatisticsListOtherStatisticsElement);
    }

    @Test
    public void returningYearProgressForEachYearAndOtherStatistics() {
        final Statistics statistics = new Statistics();
        final Map<LocalDate, Integer> daysWithEntryCount = new HashMap<>();
        daysWithEntryCount.put(LocalDate.now(), 1);
        daysWithEntryCount.put(LocalDate.now().minusYears(1), 1);
        statistics.setTotalEntries(2);
        statistics.setDaysWithEntryCount(daysWithEntryCount);

        final List<StatisticsListElement> result = converter.convert(statistics);

        assertEquals(3, result.size());
        assert (result.get(0) instanceof StatisticsListYearProgressElement);
        assert (result.get(1) instanceof StatisticsListYearProgressElement);
        assert (result.get(2) instanceof StatisticsListOtherStatisticsElement);
    }
}