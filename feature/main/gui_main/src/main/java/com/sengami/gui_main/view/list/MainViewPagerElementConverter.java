package com.sengami.gui_main.view.list;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.sengami.gui_base.view.list.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.fragment.app.Fragment;

public class MainViewPagerElementConverter implements ElementConverter<List<Fragment>, List<MainViewPagerElement>> {

    @NotNull
    @Override
    public List<MainViewPagerElement> convert(@NotNull final List<Fragment> fragments) {
        return Stream
            .of(fragments)
            .map(MainViewPagerFragmentElement::new)
            .collect(Collectors.toList());
    }
}