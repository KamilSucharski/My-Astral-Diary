package com.sengami.data_base.mapper;

import org.jetbrains.annotations.NotNull;

public interface Mapper<DBO, Model> {

    DBO toDBO(@NotNull final Model source);

    Model toModel(@NotNull final DBO source);
}
