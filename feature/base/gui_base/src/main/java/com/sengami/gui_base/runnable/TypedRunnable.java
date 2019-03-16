package com.sengami.gui_base.runnable;

@FunctionalInterface
public interface TypedRunnable<T> {
    void invoke(T object);
}