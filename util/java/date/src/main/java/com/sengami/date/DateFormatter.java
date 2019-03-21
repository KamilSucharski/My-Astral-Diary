package com.sengami.date;

import com.sengami.domain_base.Constants;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Locale;

public final class DateFormatter {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, getLocale());

    @NotNull
    public static String format(@NotNull final LocalDate localDate) {
        return simpleDateFormat.format(localDate.toDate());
    }

    private static Locale getLocale() {
        return Locale.getDefault();
    }
}