package com.sengami.gui_base.navigation;

import org.jetbrains.annotations.NotNull;

public final class FlowCoordinatorProvider {

    private static FlowCoordinator flowCoordinator;

    public static void initialize(@NotNull final FlowCoordinator flowCoordinator) {
        FlowCoordinatorProvider.flowCoordinator = flowCoordinator;
    }

    public static FlowCoordinator provide() {
        if (flowCoordinator == null) {
            throw new RuntimeException("FlowCoordinator not set.");
        }

        return flowCoordinator;
    }
}
