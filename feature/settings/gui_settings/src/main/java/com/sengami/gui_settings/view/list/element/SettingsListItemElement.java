package com.sengami.gui_settings.view.list.element;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.StringRes;

public final class SettingsListItemElement extends SettingsListElement {

    @StringRes
    private final int nameRes;
    @NotNull
    private final Runnable onClickRunnable;

    public SettingsListItemElement(@StringRes final int nameRes,
                                   @NotNull final Runnable onClickRunnable) {
        super(SettingsListElementType.ITEM);
        this.nameRes = nameRes;
        this.onClickRunnable = onClickRunnable;
    }

    @StringRes
    public int getNameRes() {
        return nameRes;
    }

    @NotNull
    public Runnable getOnClickRunnable() {
        return onClickRunnable;
    }
}