package com.sengami.gui_main.view.pager;

import android.content.Context;

import com.sengami.gui_diary.view.DiaryEntryListFragment;
import com.sengami.gui_main.R;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class MainViewPagerElementFactory {

    public List<MainViewPagerElement> create(@NotNull final Context context) {
        return Arrays.asList(
            new MainViewPagerElement(new DiaryEntryListFragment(), context.getString(R.string.tab_diary)),
            new MainViewPagerElement(new DiaryEntryListFragment(), context.getString(R.string.tab_statistics)),
            new MainViewPagerElement(new DiaryEntryListFragment(), context.getString(R.string.tab_settings))
        );
    }
}
