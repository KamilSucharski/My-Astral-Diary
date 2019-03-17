package com.sengami.domain_base.model;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {

    @Nullable
    public abstract Integer getId();

    public boolean isNew() {
        return getId() == null;
    }
}