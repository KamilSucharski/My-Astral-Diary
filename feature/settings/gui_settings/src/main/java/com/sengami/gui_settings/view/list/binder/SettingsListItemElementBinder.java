package com.sengami.gui_settings.view.list.binder;

import android.content.Context;

import com.sengami.gui_settings.databinding.ElementItemBinding;
import com.sengami.gui_settings.view.list.element.SettingsListElement;
import com.sengami.gui_settings.view.list.element.SettingsListElementType;
import com.sengami.gui_settings.view.list.element.SettingsListItemElement;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

import static com.sengami.clicks.Clicks.onClick;

public final class SettingsListItemElementBinder extends ViewHolderBinder<SettingsListElement, SettingsListItemElement, ElementItemBinding> {

    @NotNull
    private final Context context;

    public SettingsListItemElementBinder(@NotNull final Context context) {
        this.context = context;
    }

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return SettingsListElementType.ITEM;
    }

    @Override
    protected final void performBind(@NotNull final ElementItemBinding binding,
                                     @NotNull final SettingsListItemElement item) {
        binding.setName(context.getString(item.getNameRes()));
        onClick(binding, () -> item.getOnClickRunnable().run());
    }
}