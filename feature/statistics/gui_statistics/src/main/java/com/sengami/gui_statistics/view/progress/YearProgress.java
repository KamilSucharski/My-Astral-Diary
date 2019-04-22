package com.sengami.gui_statistics.view.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.annotation.DrawableRes;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_statistics.view.YearProgressView;
import com.sengami.gui_base.view.BaseView;
import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.databinding.YearProgressBinding;
import com.sengami.gui_statistics.di.component.DaggerYearProgressComponent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public final class YearProgress
    extends BaseView<Presenter<YearProgressView>, YearProgressBinding>
    implements YearProgressView {

    private static final int DAYS_PER_COLUMN = 11;
    private static final int MONTHS_IN_A_YEAR = 12;
    private static final int COLUMNS_PER_MONTH = 3;

    public YearProgress(@NotNull final Context context) {
        super(context);
    }

    public YearProgress(@NotNull final Context context,
                        @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.year_progress;
    }

    @Override
    protected View getLayoutBindingRoot() {
        return findViewById(R.id.year_progress_layout);
    }

    @Override
    protected void inject(@NotNull final Context context) {
        DaggerYearProgressComponent
            .builder()
            .build()
            .inject(this);
    }

    @Inject
    @Override
    protected void injectPresenter(@NotNull final Presenter<YearProgressView> presenter) {
        super.injectPresenter(presenter);
    }

    public void setData(final int year,
                        @NotNull final Collection<LocalDate> highlightedDays) {
        final Context context = getContext();
        binding.setYear(year);
        binding.daysLinearLayout.post(() -> {
            binding.daysLinearLayout.removeAllViews();
            final int width = binding.daysLinearLayout.getWidth();
            final TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
            for (int i = 1; i <= DAYS_PER_COLUMN; i++) {
                binding.daysLinearLayout.addView(createRow(i, width, year, context, highlightedDays), tableLayoutParams);
            }
        });
    }

    private LinearLayout createRow(final int rowNumber,
                                   final int rowWidth,
                                   final int year,
                                   @NotNull final Context context,
                                   @NotNull final Collection<LocalDate> highlightedDays) {
        final LinearLayout tableRow = new LinearLayout(context);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tableRow.setLayoutParams(layoutParams);
        tableRow.setHorizontalGravity(Gravity.CENTER);
        final int dayWidth = rowWidth / (MONTHS_IN_A_YEAR * COLUMNS_PER_MONTH);

        for (int month = 1; month <= MONTHS_IN_A_YEAR; month++) {
            for (int columnsBefore = 0; columnsBefore < COLUMNS_PER_MONTH; columnsBefore++) {
                final View dayProgress = createDayView(
                    context,
                    highlightedDays,
                    rowNumber + (DAYS_PER_COLUMN * columnsBefore),
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
                               @NotNull final Collection<LocalDate> highlightedDays,
                               final int day,
                               final int month,
                               final int year,
                               final int dayWidth) {
        final View dayView = new View(context);
        final int marginSize = dayWidth / 10;
        final int iconSize = dayWidth - (2 * marginSize);

        if (isValidDate(day, month, year)) {
            setCorrectBackgroundOnDayView(dayView, highlightedDays, day, month, year);
        }

        final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
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

    private void setCorrectBackgroundOnDayView(@NotNull final View dayView,
                                               @NotNull final Collection<LocalDate> highlightedDays,
                                               final int day,
                                               final int month,
                                               final int year) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        final LocalDate date = LocalDate.fromCalendarFields(calendar);
        dayView.setBackgroundResource(getBackgroundDrawableResource(date, highlightedDays.contains(date)));
    }

    @DrawableRes
    private int getBackgroundDrawableResource(@NotNull final LocalDate date,
                                              final boolean highlighted) {
        if (highlighted) {
            return R.drawable.background_day_progress_highlighted;
        }

        final LocalDate today = LocalDate.now();
        if (date.isAfter(today)) {
            return R.drawable.background_day_progress_future;
        } else {
            return R.drawable.background_day_progress_past;
        }
    }
}