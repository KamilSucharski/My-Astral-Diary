package com.sengami.recycler_view_adapter.converter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ElementConverter<SOURCE, RESULT> {

    @NotNull
    List<RESULT> convert(@NotNull final SOURCE source);
}