package com.sengami.gui_settings.view.list.element;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.StringRes;
import io.reactivex.subjects.Subject;

public final class SettingsListItemElement extends SettingsListElement {

    @StringRes
    private final int nameRes;
    @NotNull
    private final Subject<Boolean> clickTrigger;

    public SettingsListItemElement(@StringRes final int nameRes,
                                   @NotNull final Subject<Boolean> clickTrigger) {
        super(SettingsListElementType.ITEM);
        this.nameRes = nameRes;
        this.clickTrigger = clickTrigger;
    }

    @StringRes
    public int getNameRes() {
        return nameRes;
    }

    @NotNull
    public Subject<Boolean> getClickTrigger() {
        return clickTrigger;
    }
}