package com.sengami.gui_base.context;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

public final class ApplicationContext {

    private static HasApplicationContext hasApplicationContext;

    public static void init(@NotNull final HasApplicationContext hasApplicationContext) {
        ApplicationContext.hasApplicationContext = hasApplicationContext;
    }

    @NotNull
    public static Context get() {
        if (hasApplicationContext == null) {
            throw new RuntimeException("ApplicationContext not set.");
        }

        return hasApplicationContext.get();
    }
}
