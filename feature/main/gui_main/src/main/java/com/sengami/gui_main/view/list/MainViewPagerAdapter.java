package com.sengami.gui_main.view.list;

import android.content.Context;

import com.sengami.gui_base.view.list.BaseAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import androidx.fragment.app.FragmentManager;

public final class MainViewPagerAdapter extends BaseAdapter<MainViewPagerElement, MainViewPagerElementType> {

    public MainViewPagerAdapter(@NotNull final Context context,
                                @NotNull final FragmentManager fragmentManager) {
        super(
            context,
            MainViewPagerElementType.values(),
            new ArrayList<>(Collections.singletonList(
                new MainViewPagerFragmentElementBinder(fragmentManager)
            ))
        );
    }
}