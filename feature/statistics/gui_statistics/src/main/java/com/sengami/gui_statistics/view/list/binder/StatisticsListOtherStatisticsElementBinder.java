package com.sengami.gui_statistics.view.list.binder;

import com.sengami.gui_statistics.databinding.ElementOtherStatisticsBinding;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElementType;
import com.sengami.gui_statistics.view.list.element.StatisticsListOtherStatisticsElement;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

public final class StatisticsListOtherStatisticsElementBinder extends ViewHolderBinder<StatisticsListElement, StatisticsListOtherStatisticsElement, ElementOtherStatisticsBinding> {

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return StatisticsListElementType.OTHER_STATISTICS;
    }

    @Override
    protected final void performBind(@NotNull final ElementOtherStatisticsBinding binding,
                                     @NotNull final StatisticsListOtherStatisticsElement item) {
        binding.setStatistics(item.getStatistics());
    }
}