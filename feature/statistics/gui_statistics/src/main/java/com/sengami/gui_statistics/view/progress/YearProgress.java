package com.sengami.gui_statistics.view.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

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
        binding.daysLinearLayout.removeAllViews();
        final TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        for (int i = 1; i <= DAYS_PER_COLUMN; i++) {
            binding.daysLinearLayout.addView(createRow(i, year, context, highlightedDays), tableLayoutParams);
        }
    }

    private LinearLayout createRow(final int rowNumber,
                                   final int year,
                                   @NotNull final Context context,
                                   @NotNull final Collection<LocalDate> highlightedDays) {
        final LinearLayout tableRow = new LinearLayout(context);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tableRow.setLayoutParams(layoutParams);

        for (int month = 1; month <= MONTHS_IN_A_YEAR; month++) {
            for (int columnsBefore = 0; columnsBefore < COLUMNS_PER_MONTH; columnsBefore++) {
                final DayProgress dayProgress = createDayProgress(
                    rowNumber + (DAYS_PER_COLUMN * columnsBefore),
                    month,
                    year,
                    context,
                    highlightedDays
                );

                tableRow.addView(dayProgress);
            }
        }
        return tableRow;
    }

    @NotNull
    private DayProgress createDayProgress(final int day,
                                          final int month,
                                          final int year,
                                          @NotNull final Context context,
                                          @NotNull final Collection<LocalDate> highlightedDays) {
        final DayProgress dayProgress = new DayProgress(context);
        if (isValidDate(day, month, year)) {
            final Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);
            final LocalDate date = LocalDate.fromCalendarFields(calendar);
            dayProgress.setDay(date, highlightedDays.contains(date));
        }
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        );
        dayProgress.setLayoutParams(layoutParams);
        return dayProgress;
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
}