package com.sengami.myastraldiary.application;

import android.app.Application;

import com.sengami.gui_base.navigation.FlowCoordinatorProvider;
import com.sengami.myastraldiary.navigation.ApplicationFlowCoordinator;

public final class MyAstralDiaryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowCoordinatorProvider.initialize(new ApplicationFlowCoordinator());
    }
}