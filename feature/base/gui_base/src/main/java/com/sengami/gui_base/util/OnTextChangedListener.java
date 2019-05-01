package com.sengami.gui_base.util;

import android.text.Editable;
import android.text.TextWatcher;

import org.jetbrains.annotations.NotNull;

public abstract class OnTextChangedListener implements TextWatcher {

    protected abstract void onTextChanged(@NotNull final String text);

    public void beforeTextChanged(final CharSequence s,
                                  final int start,
                                  final int count,
                                  final int after) {
    }

    @Override
    public void onTextChanged(final CharSequence s,
                              final int start,
                              final int before,
                              final int count) {
    }

    @Override
    public void afterTextChanged(final Editable s) {
        onTextChanged(s.toString().trim());
    }
}