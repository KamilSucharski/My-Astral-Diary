package com.sengami.gui_statistics.view.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.DrawableRes;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_statistics.view.DayProgressView;
import com.sengami.gui_base.view.BaseView;
import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.databinding.DayProgressBinding;
import com.sengami.gui_statistics.di.component.DaggerDayProgressComponent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.LocalDate;

import javax.inject.Inject;

public final class DayProgress
    extends BaseView<Presenter<DayProgressView>, DayProgressBinding>
    implements DayProgressView {

    public DayProgress(@NotNull final Context context) {
        super(context);
    }

    public DayProgress(@NotNull final Context context,
                       @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.day_progress;
    }

    @Override
    protected View getLayoutBindingRoot() {
        return findViewById(R.id.day_progress);
    }

    @Override
    protected void inject(@NotNull final Context context) {
        DaggerDayProgressComponent
            .builder()
            .build()
            .inject(this);
    }

    @Inject
    @Override
    protected void injectPresenter(@NotNull final Presenter<DayProgressView> presenter) {
        super.injectPresenter(presenter);
    }

    public void setDay(@NotNull final LocalDate date,
                       final boolean highlighted) {
        binding.dayProgress.setBackgroundResource(getBackgroundDrawableResource(date, highlighted));
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