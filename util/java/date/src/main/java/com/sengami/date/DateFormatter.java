package com.sengami.date;

import com.sengami.domain_base.Constants;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateFormatter {

    @NotNull
    public static String format(@NotNull final Date date) {
        return format(date, Constants.DEFAULT_DATE_FORMAT);
    }

    @NotNull
    public static String format(@NotNull final Date date,
                                @NotNull final String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }
}