package com.sengami.gui_settings.view.list.element;

import com.sengami.recycler_view_adapter.element.Element;

import org.jetbrains.annotations.NotNull;

public abstract class SettingsListElement implements Element {

    @NotNull
    private final SettingsListElementType type;

    public SettingsListElement(@NotNull final SettingsListElementType type) {
        this.type = type;
    }

    @Override
    @NotNull
    public SettingsListElementType getType() {
        return type;
    }
}