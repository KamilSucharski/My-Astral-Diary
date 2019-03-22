package com.sengami.gui_statistics.view.list.binder;

import android.content.Context;

import com.sengami.gui_statistics.databinding.ElementChartBinding;
import com.sengami.gui_statistics.view.list.element.StatisticsListChartElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElementType;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

public final class StatisticsListChartElementBinder extends ViewHolderBinder<StatisticsListElement, StatisticsListChartElement, ElementChartBinding> {

    @NotNull
    private final Context context;

    public StatisticsListChartElementBinder(@NotNull final Context context) {
        this.context = context;
    }

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return StatisticsListElementType.CHART;
    }

    @Override
    protected final void performBind(@NotNull final ElementChartBinding binding,
                                     @NotNull final StatisticsListChartElement item) {
    }
}