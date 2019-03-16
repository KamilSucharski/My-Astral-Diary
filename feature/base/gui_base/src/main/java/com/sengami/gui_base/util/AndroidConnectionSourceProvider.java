package com.sengami.gui_base.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.data_base.util.ConnectionSourceProvider;
import com.sengami.domain_base.util.Constants;
import com.sengami.gui_base.BuildConfig;

import org.jetbrains.annotations.NotNull;

public final class AndroidConnectionSourceProvider extends OrmLiteSqliteOpenHelper implements ConnectionSourceProvider {

    public AndroidConnectionSourceProvider(@NotNull final Context context) {
        super(context, Constants.DATABASE_NAME, null, BuildConfig.VERSION_CODE);
    }

    @Override
    public @NotNull ConnectionSource provide() {
        return new AndroidConnectionSource(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

    @Override
    public void close() {
        super.close();
    }
}