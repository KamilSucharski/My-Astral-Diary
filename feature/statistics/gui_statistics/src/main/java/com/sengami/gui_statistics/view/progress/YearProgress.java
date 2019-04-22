package com.sengami.gui_statistics.view.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_statistics.view.DayProgressView;
import com.sengami.gui_base.view.BaseView;
import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.databinding.DayProgressBinding;
import com.sengami.gui_statistics.di.component.DaggerDayProgressComponent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;

public final class YearProgress
    extends BaseView<Presenter<DayProgressView>, DayProgressBinding>
    implements DayProgressView {

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
        DaggerDayProgressComponent
            .builder()
            .build()
            .inject(this);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setData(final int year,
                        @NotNull final List<LocalDate> daysWithEntries) {

    }
}