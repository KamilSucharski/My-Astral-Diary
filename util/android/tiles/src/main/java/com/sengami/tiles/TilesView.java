package com.sengami.tiles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sengami.tiles.exception.TileViewConfigurationNotSetInTilesViewException;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public final class TilesView extends LinearLayout {

    private TilesViewConfiguration configuration;

    public TilesView(@NotNull final Context context) {
        this(context, null);
    }

    public TilesView(@NotNull final Context context,
                     @Nullable final AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public void configure(@NotNull final TilesViewConfiguration configuration) {
        this.configuration = configuration;
    }

    public void display(@NotNull final Map<LocalDate, Integer> highlightedDays) {
        if (configuration == null) {
            throw new TileViewConfigurationNotSetInTilesViewException();
        }

        final Context context = getContext();
        getRootView().post(() -> {
            removeAllViews();
            final int width = getWidth();
            for (int i = 1; i <= configuration.getDaysPerColumn(); i++) {
                addView(createRow(i, width, configuration.getYear(), context, highlightedDays));
            }
        });
    }

    private LinearLayout createRow(final int rowNumber,
                                   final int rowWidth,
                                   final int year,
                                   @NotNull final Context context,
                                   @NotNull final Map<LocalDate, Integer> highlightedDays) {
        final LinearLayout tableRow = new LinearLayout(context);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tableRow.setLayoutParams(layoutParams);
        tableRow.setHorizontalGravity(Gravity.CENTER);
        final int dayWidth = rowWidth / (TilesConstants.MONTHS_IN_A_YEAR * configuration.getColumnsPerMonth());

        for (int month = 1; month <= TilesConstants.MONTHS_IN_A_YEAR; month++) {
            for (int columnsBefore = 0; columnsBefore < configuration.getColumnsPerMonth(); columnsBefore++) {
                final View dayProgress = createDayView(
                    context,
                    highlightedDays,
                    rowNumber + (configuration.getDaysPerColumn() * columnsBefore),
                    month,
                    year,
                    dayWidth
                );

                tableRow.addView(dayProgress);
            }
        }
        return tableRow;
    }

    @NotNull
    private View createDayView(@NotNull final Context context,
                               @NotNull final Map<LocalDate, Integer> highlightedDays,
                               final int day,
                               final int month,
                               final int year,
                               final int dayWidth) {
        final View dayView = new View(context);
        final int marginSize = dayWidth / 10;
        final int iconSize = dayWidth - (2 * marginSize);

        if (isValidDate(day, month, year)) {
            setCorrectBackgroundOnView(dayView, highlightedDays, day, month, year);
        }

        final LayoutParams layoutParams = new LayoutParams(
            iconSize,
            iconSize
        );
        layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize);
        dayView.setLayoutParams(layoutParams);

        return dayView;
    }

    private boolean isValidDate(final int day,
                                final int month,
                                final int year) {
        return isValid31DaysMonthDate(day, month)
            || isValid30DaysMonthDate(day, month)
            || isValidFebruaryDate(day, month, year);
    }

    private boolean isValid31DaysMonthDate(final int day,
                                           final int month) {
        final List<Integer> monthsWith31Days = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        return monthsWith31Days.contains(month) && day <= 31;
    }

    private boolean isValid30DaysMonthDate(final int day,
                                           final int month) {
        final List<Integer> monthsWith30Days = Arrays.asList(4, 6, 9, 11);
        return monthsWith30Days.contains(month) && day <= 30;
    }

    private boolean isValidFebruaryDate(final int day,
                                        final int month,
                                        final int year) {
        final int februaryDayCount = year % 4 == 0 ? 29 : 28;
        return month == 2 && day <= februaryDayCount;
    }

    private void setCorrectBackgroundOnView(@NotNull final View view,
                                            @NotNull final Map<LocalDate, Integer> highlightedDays,
                                            final int day,
                                            final int month,
                                            final int year) {
        final TileDecorator tileDecorator = configuration.getTileDecorator();

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        final LocalDate date = LocalDate.fromCalendarFields(calendar);
        final LocalDate today = LocalDate.now();

        final Integer suppliedNumber = highlightedDays.get(date);
        final int number = suppliedNumber != null ? suppliedNumber : 0;

        if (date.isBefore(today)) {
            tileDecorator.decoratePastCell(view, number);
            return;
        }

        if (date.isAfter(today)) {
            tileDecorator.decorateFutureCell(view, number);
            return;
        }

        tileDecorator.decorateTodayCell(view, number);
    }
}