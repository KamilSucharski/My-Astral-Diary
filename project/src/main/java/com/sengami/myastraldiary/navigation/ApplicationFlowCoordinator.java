package com.sengami.myastraldiary.navigation;

import android.content.Context;
import android.content.Intent;

import com.sengami.gui_base.navigation.FlowCoordinator;
import com.sengami.gui_diary.view.DiaryEntryComposerActivity;
import com.sengami.gui_diary.view.DiaryEntryListFragment;
import com.sengami.gui_main.view.MainActivity;
import com.sengami.gui_settings.view.SettingsFragment;
import com.sengami.gui_statistics.view.StatisticsFragment;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;

public final class ApplicationFlowCoordinator implements FlowCoordinator {
    
    @Override
    @NotNull
    public Intent mainActivityIntent(@NotNull final Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    @NotNull
    public Intent diaryEntryComposerActivityIntent(@NotNull final Context context) {
        return new Intent(context, DiaryEntryComposerActivity.class);
    }

    @Override
    @NotNull
    public Fragment diaryEntryListFragment() {
        return new DiaryEntryListFragment();
    }

    @Override
    @NotNull
    public Fragment statisticsFragment() {
        return new StatisticsFragment();
    }

    @Override
    @NotNull
    public Fragment settingsFragment() {
        return new SettingsFragment();
    }
}