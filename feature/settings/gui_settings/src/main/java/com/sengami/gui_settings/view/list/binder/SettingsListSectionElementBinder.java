package com.sengami.gui_settings.view.list.binder;

import android.content.Context;

import com.sengami.gui_settings.databinding.ElementSectionBinding;
import com.sengami.gui_settings.view.list.element.SettingsListElement;
import com.sengami.gui_settings.view.list.element.SettingsListElementType;
import com.sengami.gui_settings.view.list.element.SettingsListSectionElement;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

public final class SettingsListSectionElementBinder extends ViewHolderBinder<SettingsListElement, SettingsListSectionElement, ElementSectionBinding> {

    @NotNull
    private final Context context;

    public SettingsListSectionElementBinder(@NotNull final Context context) {
        this.context = context;
    }

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return SettingsListElementType.SECTION;
    }

    @Override
    protected final void performBind(@NotNull final ElementSectionBinding binding,
                                     @NotNull final SettingsListSectionElement item) {
        binding.setName(context.getString(item.getNameRes()));
    }
}