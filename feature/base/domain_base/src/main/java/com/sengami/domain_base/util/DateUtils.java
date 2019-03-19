package com.sengami.domain_base.util;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Locale;

public final class DateUtils {

    private static final String DATE_FORMAT = "dd MMMM yyyy";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    @NotNull
    public static String format(@NotNull final LocalDate localDate) {
        return simpleDateFormat.format(localDate.toDate());
    }
}