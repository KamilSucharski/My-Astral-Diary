package com.sengami.gui_main.view.pager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public final class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<MainViewPagerElement> items;

    public MainViewPagerAdapter(@NotNull final FragmentManager fragmentManager,
                                @NotNull final List<MainViewPagerElement> items) {
        super(fragmentManager);
        this.items = items;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position).getFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return items.get(position).getPageTitle();
    }

    @Override
    public int getCount() {
        return items.size();
    }
}