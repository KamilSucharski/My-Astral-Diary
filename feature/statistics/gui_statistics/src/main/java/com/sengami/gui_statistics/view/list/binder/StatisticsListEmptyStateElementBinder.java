package com.sengami.gui_statistics.view.list.binder;

import com.sengami.gui_statistics.databinding.ElementStatisticsEmptyStateBinding;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElementType;
import com.sengami.gui_statistics.view.list.element.StatisticsListEmptyStateElement;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

public final class StatisticsListEmptyStateElementBinder extends ViewHolderBinder<StatisticsListElement, StatisticsListEmptyStateElement, ElementStatisticsEmptyStateBinding> {

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return StatisticsListElementType.EMPTY_STATE;
    }

    @Override
    protected final void performBind(@NotNull final ElementStatisticsEmptyStateBinding binding,
                                     @NotNull final StatisticsListEmptyStateElement item) {
    }
}