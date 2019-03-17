package com.sengami.gui_base.view.list.adapter;

import org.jetbrains.annotations.NotNull;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public final class BaseViewHolder extends RecyclerView.ViewHolder {

    @NotNull
    private final ViewDataBinding binding;

    public BaseViewHolder(@NotNull final ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @NotNull
    public ViewDataBinding getBinding() {
        return binding;
    }
}
