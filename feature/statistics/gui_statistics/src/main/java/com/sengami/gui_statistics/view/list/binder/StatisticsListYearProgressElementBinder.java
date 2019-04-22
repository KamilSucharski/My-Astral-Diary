package com.sengami.gui_statistics.view.list.binder;

import com.sengami.gui_statistics.databinding.ElementYearProgressBinding;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElementType;
import com.sengami.gui_statistics.view.list.element.StatisticsListYearProgressElement;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

public final class StatisticsListYearProgressElementBinder extends ViewHolderBinder<StatisticsListElement, StatisticsListYearProgressElement, ElementYearProgressBinding> {

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return StatisticsListElementType.YEAR_PROGRESS;
    }

    @Override
    protected final void performBind(@NotNull final ElementYearProgressBinding binding,
                                     @NotNull final StatisticsListYearProgressElement item) {
        binding.yearProgress.setData(item.getYear(), item.getHighlightedDays());
    }
}