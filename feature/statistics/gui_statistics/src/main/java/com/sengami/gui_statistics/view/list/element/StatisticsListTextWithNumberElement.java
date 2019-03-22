package com.sengami.gui_statistics.view.list.element;

import androidx.annotation.StringRes;

public final class StatisticsListTextWithNumberElement extends StatisticsListElement {

    @StringRes
    private final int textRes;
    private final int number;

    public StatisticsListTextWithNumberElement(@StringRes final int textRes,
                                               final int number) {
        super(StatisticsListElementType.TEXT_WITH_NUMBER);
        this.textRes = textRes;
        this.number = number;
    }

    @StringRes
    public int getTextRes() {
        return textRes;
    }

    public int getNumber() {
        return number;
    }
}