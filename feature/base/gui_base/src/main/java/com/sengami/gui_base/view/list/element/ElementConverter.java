package com.sengami.gui_base.view.list.element;

import org.jetbrains.annotations.NotNull;

public interface ElementConverter<SOURCE, RESULT> {

    @NotNull
    RESULT convert(@NotNull final SOURCE source);
}