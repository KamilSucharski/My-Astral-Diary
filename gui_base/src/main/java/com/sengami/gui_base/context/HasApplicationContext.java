package com.sengami.gui_base.context;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

public interface HasApplicationContext {

    @NotNull
    Context get();
}
