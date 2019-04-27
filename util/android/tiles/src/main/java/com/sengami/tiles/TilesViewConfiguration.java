package com.sengami.tiles;

import com.sengami.tiles.exception.NotEnoughColumnsPerMonthOrDaysPerColumnException;
import com.sengami.tiles.exception.TileDecoratorNotSetInTilesViewConfigurationException;

import org.jetbrains.annotations.NotNull;

public final class TilesViewConfiguration {

    private TilesViewConfiguration() {}

    private int year = TilesConstants.DEFAULT_YEAR;
    private int daysPerColumn = TilesConstants.DEFAULT_DAYS_PER_COLUMN;
    private int columnsPerMonth = TilesConstants.DEFAULT_COLUMNS_PER_MONTH;
    private TileDecorator tileDecorator;

    public int getYear() {
        return year;
    }

    public int getDaysPerColumn() {
        return daysPerColumn;
    }

    public int getColumnsPerMonth() {
        return columnsPerMonth;
    }

    public TileDecorator getTileDecorator() {
        return tileDecorator;
    }

    public static final class Builder {

        private final TilesViewConfiguration configuration;

        public Builder() {
            configuration = new TilesViewConfiguration();
        }

        public Builder year(final int year) {
            configuration.year = year;
            return this;
        }

        public Builder daysPerColumn(final int daysPerColumn) {
            configuration.daysPerColumn = daysPerColumn;
            return this;
        }

        public Builder columnsPerMonth(final int columnsPerMonth) {
            configuration.columnsPerMonth = columnsPerMonth;
            return this;
        }

        public Builder tileDecorator(@NotNull final TileDecorator tileDecorator) {
            configuration.tileDecorator = tileDecorator;
            return this;
        }

        public TilesViewConfiguration build() {
            validate();
            return configuration;
        }

        private void validate() {
            if (configuration.tileDecorator == null) {
                throw new TileDecoratorNotSetInTilesViewConfigurationException();
            }

            if (configuration.daysPerColumn * configuration.columnsPerMonth < TilesConstants.LONGEST_MONTH_DAY_COUNT) {
                throw new NotEnoughColumnsPerMonthOrDaysPerColumnException();
            }
        }
    }
}