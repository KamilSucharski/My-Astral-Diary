package com.sengami.gui_base.runnable;

import android.content.Context;

@FunctionalInterface
public interface ContextRunnable {
    void invoke(Context context);
}