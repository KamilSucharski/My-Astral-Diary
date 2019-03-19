package com.sengami.recycler_view_adapter.adapter;

import org.jetbrains.annotations.NotNull;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public final class BaseViewHolder extends RecyclerView.ViewHolder {

    @NotNull
    private final ViewDataBinding binding;

    protected BaseViewHolder(@NotNull final ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @NotNull
    public ViewDataBinding getBinding() {
        return binding;
    }
}
