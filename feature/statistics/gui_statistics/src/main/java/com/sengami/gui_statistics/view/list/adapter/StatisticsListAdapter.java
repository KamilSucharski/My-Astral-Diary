package com.sengami.gui_statistics.view.list.adapter;

import com.sengami.gui_statistics.view.list.binder.StatisticsListEmptyStateElementBinder;
import com.sengami.gui_statistics.view.list.binder.StatisticsListOtherStatisticsElementBinder;
import com.sengami.gui_statistics.view.list.binder.StatisticsListYearProgressElementBinder;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElementType;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;

import java.util.Arrays;

public final class StatisticsListAdapter extends BaseAdapter<StatisticsListElement, StatisticsListElementType> {

    public StatisticsListAdapter() {
        super(
            StatisticsListElementType.values(),
            Arrays.asList(
                new StatisticsListYearProgressElementBinder(),
                new StatisticsListOtherStatisticsElementBinder(),
                new StatisticsListEmptyStateElementBinder()
            )
        );
    }
}