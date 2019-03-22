package com.sengami.gui_statistics.view.list.binder;

import android.content.Context;

import com.sengami.gui_statistics.databinding.ElementTextWithNumberBinding;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElementType;
import com.sengami.gui_statistics.view.list.element.StatisticsListTextWithNumberElement;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

public final class StatisticsListTextWithNumberElementBinder extends ViewHolderBinder<StatisticsListElement, StatisticsListTextWithNumberElement, ElementTextWithNumberBinding> {

    @NotNull
    private final Context context;

    public StatisticsListTextWithNumberElementBinder(@NotNull final Context context) {
        this.context = context;
    }

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return StatisticsListElementType.TEXT_WITH_NUMBER;
    }

    @Override
    protected final void performBind(@NotNull final ElementTextWithNumberBinding binding,
                                     @NotNull final StatisticsListTextWithNumberElement item) {
        binding.setText(context.getString(item.getTextRes()));
        binding.setNumber(item.getNumber());
    }
}