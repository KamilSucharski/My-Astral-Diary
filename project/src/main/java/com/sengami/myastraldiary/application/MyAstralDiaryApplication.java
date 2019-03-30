package com.sengami.myastraldiary.application;

import android.app.Application;

import com.sengami.gui_base.database.DatabaseConnectionProviderHolder;
import com.sengami.gui_base.navigation.FlowCoordinatorProvider;
import com.sengami.myastraldiary.navigation.ApplicationFlowCoordinator;

public final class MyAstralDiaryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseConnectionProviderHolder.initialize(this);
        FlowCoordinatorProvider.initialize(new ApplicationFlowCoordinator());
    }
}