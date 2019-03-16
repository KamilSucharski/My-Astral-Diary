package com.sengami.gui_main.view.list;

import com.sengami.gui_base.view.list.BaseViewHolder;
import com.sengami.gui_base.view.list.ViewHolderBinder;
import com.sengami.gui_main.R;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public final class MainViewPagerFragmentElementBinder extends ViewHolderBinder<MainViewPagerElement, MainViewPagerFragmentElement> {

    @NotNull
    private final FragmentManager fragmentManager;

    public MainViewPagerFragmentElementBinder(@NotNull final FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected int getTypeOrdinal() {
        return MainViewPagerElementType.FRAGMENT.ordinal();
    }

    @Override
    protected void performBind(@NotNull final BaseViewHolder viewHolder,
                               @NotNull final MainViewPagerFragmentElement item) {
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, item.getFragment());
        fragmentTransaction.commit();
    }
}