package com.sengami.database_connection.implementation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.database_connection.BuildConfig;
import com.sengami.domain_base.Constants;

import org.jetbrains.annotations.NotNull;

public final class DatabaseDatabaseConnectionProvider extends OrmLiteSqliteOpenHelper implements com.sengami.data_base.util.DatabaseConnectionProvider {

    public DatabaseDatabaseConnectionProvider(@NotNull final Context context) {
        super(context, Constants.DATABASE_NAME, null, BuildConfig.VERSION_CODE);
    }

    @Override
    @NotNull
    public final ConnectionSource provide() {
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