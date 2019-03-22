package com.sengami.gui_statistics.view.list.element;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.StringRes;

public final class StatisticsListTextWithNumberElement extends StatisticsListElement {

    @StringRes
    private final int textRes;
    @NotNull
    private final String number;

    public StatisticsListTextWithNumberElement(@StringRes final int textRes,
                                               @NotNull final String number) {
        super(StatisticsListElementType.TEXT_WITH_NUMBER);
        this.textRes = textRes;
        this.number = number;
    }

    @StringRes
    public int getTextRes() {
        return textRes;
    }

    @NotNull
    public String getNumber() {
        return number;
    }
}