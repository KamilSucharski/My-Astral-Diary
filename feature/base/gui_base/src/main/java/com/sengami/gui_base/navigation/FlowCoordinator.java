package com.sengami.gui_base.navigation;

import android.content.Context;
import android.content.Intent;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;

public interface FlowCoordinator {

    @NotNull
    Intent mainActivityIntent(@NotNull final Context context);
    @NotNull
    Intent diaryEntryComposerActivityIntent(@NotNull final Context context);
    @NotNull
    Fragment diaryEntryListFragment();
    @NotNull
    Fragment statisticsFragment();
    @NotNull
    Fragment settingsFragment();
}