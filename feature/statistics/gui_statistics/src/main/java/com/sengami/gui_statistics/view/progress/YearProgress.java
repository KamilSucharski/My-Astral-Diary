package com.sengami.gui_statistics.view.progress;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_statistics.view.YearProgressView;
import com.sengami.gui_base.view.BaseView;
import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.databinding.YearProgressBinding;
import com.sengami.gui_statistics.di.component.DaggerYearProgressComponent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.Collection;

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
        final TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        for (int i = 1; i <= DAYS_PER_COLUMN; i++) {
            binding.daysTable.addView(createRow(i, year, context, highlightedDays), tableLayoutParams);
        }
        binding.daysTable.requestLayout();
    }

    private TableRow createRow(final int rowNumber,
                               final int year,
                               @NotNull final Context context,
                               @NotNull final Collection<LocalDate> highlightedDays) {
        final TableRow tableRow = new TableRow(context);
        final TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tableRow.setLayoutParams(layoutParams);
        tableRow.setBackgroundColor(Color.BLUE);

        for (int month = 1; month <= MONTHS_IN_A_YEAR; month++) {
            for (int columnsBefore = 0; columnsBefore < COLUMNS_PER_MONTH; columnsBefore++) {
                final DayProgress dayProgress = createDayProgress(
                    rowNumber + (DAYS_PER_COLUMN * columnsBefore),
                    month,
                    year,
                    context,
                    highlightedDays
                );

                if (dayProgress != null) {
                    tableRow.addView(dayProgress);
                    TextView textView = new TextView(context);
                    final LinearLayout.LayoutParams testLayoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    textView.setLayoutParams(testLayoutParams);
                    textView.setText("dsadsad");
                    tableRow.addView(textView);
                }
            }
        }
        tableRow.invalidate();
        return tableRow;
    }

    @Nullable
    private DayProgress createDayProgress(final int day,
                                          final int month,
                                          final int year,
                                          @NotNull final Context context,
                                          @NotNull final Collection<LocalDate> highlightedDays) {
        try {
            final Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            final LocalDate date = LocalDate.fromCalendarFields(calendar);
            final DayProgress dayProgress = new DayProgress(context);
            dayProgress.setDay(date, highlightedDays.contains(date));
            final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
            );
            dayProgress.setLayoutParams(layoutParams);
            return dayProgress;
        } catch (final Exception e) {
            return null;
        }
    }
}