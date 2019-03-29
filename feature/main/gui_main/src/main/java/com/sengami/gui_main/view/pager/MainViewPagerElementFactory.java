package com.sengami.gui_main.view.pager;

import android.content.Context;

import com.sengami.gui_base.navigation.FlowCoordinator;
import com.sengami.gui_base.navigation.FlowCoordinatorProvider;
import com.sengami.gui_main.R;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class MainViewPagerElementFactory {

    public List<MainViewPagerElement> create(@NotNull final Context context) {
        final FlowCoordinator flowCoordinator = FlowCoordinatorProvider.provide();
        return Arrays.asList(
            new MainViewPagerElement(flowCoordinator.diaryEntryListFragment(), context.getString(R.string.tab_diary)),
            new MainViewPagerElement(flowCoordinator.statisticsFragment(), context.getString(R.string.tab_statistics)),
            new MainViewPagerElement(flowCoordinator.settingsFragment(), context.getString(R.string.tab_settings))
        );
    }
}