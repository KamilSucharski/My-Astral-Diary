package com.sengami.myastraldiary;

import android.app.Application;
import android.content.Context;

import com.sengami.gui_base.context.ApplicationContext;
import com.sengami.gui_base.context.HasApplicationContext;

import org.jetbrains.annotations.NotNull;

public class MyAstralDiaryApplication extends Application implements HasApplicationContext {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContext.init(this);
    }

    @Override
    @NotNull
    public Context get() {
        return this;
    }
}