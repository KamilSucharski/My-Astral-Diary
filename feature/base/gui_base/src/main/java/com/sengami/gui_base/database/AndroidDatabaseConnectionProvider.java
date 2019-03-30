package com.sengami.gui_base.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.util.DatabaseConnectionProvider;
import com.sengami.domain_base.Constants;
import com.sengami.gui_base.BuildConfig;

import org.jetbrains.annotations.NotNull;

public final class AndroidDatabaseConnectionProvider extends OrmLiteSqliteOpenHelper implements DatabaseConnectionProvider {

    public AndroidDatabaseConnectionProvider(@NotNull final Context context) {
        super(context, Constants.DATABASE_NAME, null, BuildConfig.VERSION_CODE);
    }

    @Override
    @NotNull
    public ConnectionSource provide() {
        return new AndroidConnectionSource(this);
    }

    @Override
    public void onConfigure(final SQLiteDatabase db) {
        super.onConfigure(db);
        db.disableWriteAheadLogging();
    }

    @Override
    public void onCreate(final SQLiteDatabase db,
                         final ConnectionSource connectionSource) {
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db,
                          final ConnectionSource connectionSource,
                          final int oldVersion,
                          final int newVersion) {
    }
}