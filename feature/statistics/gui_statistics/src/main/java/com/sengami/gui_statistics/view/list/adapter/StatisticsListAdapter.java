package com.sengami.gui_statistics.view.list.adapter;

import android.content.Context;

import com.sengami.gui_statistics.view.list.binder.StatisticsListEmptyStateElementBinder;
import com.sengami.gui_statistics.view.list.binder.StatisticsListTextWithNumberElementBinder;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElementType;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class StatisticsListAdapter extends BaseAdapter<StatisticsListElement, StatisticsListElementType> {

    public StatisticsListAdapter(@NotNull final Context context) {
        super(
            context,
            StatisticsListElementType.values(),
            Arrays.asList(
                new StatisticsListTextWithNumberElementBinder(context),
                new StatisticsListEmptyStateElementBinder()
            )
        );
    }
}