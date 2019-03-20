package com.sengami.gui_settings.view.list.element;

import androidx.annotation.StringRes;

public final class SettingsListSectionElement extends SettingsListElement {

    @StringRes
    private final int nameRes;

    public SettingsListSectionElement(@StringRes final int nameRes) {
        super(SettingsListElementType.SECTION);
        this.nameRes = nameRes;
    }

    @StringRes
    public int getNameRes() {
        return nameRes;
    }
}