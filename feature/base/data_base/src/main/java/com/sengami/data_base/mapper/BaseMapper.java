package com.sengami.data_base.mapper;

import org.jetbrains.annotations.NotNull;

public interface BaseMapper<DBO, Model> {

    DBO toDBO(@NotNull final Model source);

    Model toModel(@NotNull final DBO source);
}
