package com.sengami.gui_statistics.view.list.binder;

import android.view.View;

import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.databinding.ElementYearProgressBinding;
import com.sengami.gui_statistics.view.list.element.StatisticsListElement;
import com.sengami.gui_statistics.view.list.element.StatisticsListElementType;
import com.sengami.gui_statistics.view.list.element.StatisticsListYearProgressElement;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;
import com.sengami.tiles.TileDecorator;
import com.sengami.tiles.TilesViewConfiguration;

import org.jetbrains.annotations.NotNull;

public final class StatisticsListYearProgressElementBinder extends ViewHolderBinder<StatisticsListElement, StatisticsListYearProgressElement, ElementYearProgressBinding> {

    private final TileDecorator tileDecorator = createTileDecorator();

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return StatisticsListElementType.YEAR_PROGRESS;
    }

    @Override
    protected final void performBind(@NotNull final ElementYearProgressBinding binding,
                                     @NotNull final StatisticsListYearProgressElement item) {
        final TilesViewConfiguration configuration = createConfiguration(item.getYear());
        binding.setYear(item.getYear());
        binding.tilesView.configure(configuration);
        binding.tilesView.display(item.getHighlightedDays());
    }

    private TilesViewConfiguration createConfiguration(final int year) {
        return new TilesViewConfiguration.Builder()
            .year(year)
            .tileDecorator(tileDecorator)
            .build();
    }

    private TileDecorator createTileDecorator() {
        return new TileDecorator() {
            @Override
            public void decoratePastCell(final View view, final int number) {
                if (number > 0) {
                    view.setBackgroundResource(R.drawable.background_day_progress_highlighted);
                } else {
                    view.setBackgroundResource(R.drawable.background_day_progress_past);
                }
            }

            @Override
            public void decorateTodayCell(final View view, final int number) {
                if (number > 0) {
                    view.setBackgroundResource(R.drawable.background_day_progress_highlighted);
                } else {
                    view.setBackgroundResource(R.drawable.background_day_progress_past);
                }
            }

            @Override
            public void decorateFutureCell(final View view, final int number) {
                if (number > 0) {
                    view.setBackgroundResource(R.drawable.background_day_progress_highlighted);
                } else {
                    view.setBackgroundResource(R.drawable.background_day_progress_future);
                }
            }
        };
    }
}