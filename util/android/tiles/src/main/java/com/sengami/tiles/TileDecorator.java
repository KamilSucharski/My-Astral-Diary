package com.sengami.tiles;

import android.view.View;

public interface TileDecorator {

    void decoratePastCell(final View view,
                          final int number);

    void decorateTodayCell(final View view,
                           final int number);

    void decorateFutureCell(final View view,
                            final int number);
}