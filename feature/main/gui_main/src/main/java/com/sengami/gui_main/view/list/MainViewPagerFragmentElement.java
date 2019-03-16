package com.sengami.gui_main.view.list;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;

public final class MainViewPagerFragmentElement extends MainViewPagerElement {

    @NotNull
    private final Fragment fragment;

    public MainViewPagerFragmentElement(@NotNull final Fragment fragment) {
        super(MainViewPagerElementType.FRAGMENT);
        this.fragment = fragment;
    }

    @NotNull
    public Fragment getFragment() {
        return fragment;
    }
}