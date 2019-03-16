package com.sengami.gui_main.view.pager;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;

public final class MainViewPagerElement {

    @NotNull
    private final Fragment fragment;
    @NotNull
    private final String pageTitle;

    public MainViewPagerElement(@NotNull final Fragment fragment,
                                @NotNull final String pageTitle) {
        this.fragment = fragment;
        this.pageTitle = pageTitle;
    }

    @NotNull
    public Fragment getFragment() {
        return fragment;
    }

    @NotNull
    public String getPageTitle() {
        return pageTitle;
    }
}