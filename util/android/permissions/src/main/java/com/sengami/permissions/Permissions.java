package com.sengami.permissions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class Permissions {

    @FunctionalInterface
    public interface Callback extends Serializable {
        void onGranted();
    }

    @FunctionalInterface
    public interface AdvancedCallback extends Serializable {
        void onGrantedOrDenied(final boolean granted);
    }

    public static void withPermission(@NotNull final Context context,
                                      @NotNull final String permission,
                                      @NotNull final Callback callback) {
        withPermission(context, new String[]{permission}, callback);
    }

    public static void withPermission(@NotNull final Context context,
                                      @NotNull final String permission,
                                      @NotNull final AdvancedCallback callback) {
        withPermission(context, new String[]{permission}, callback);
    }

    public static void withPermission(@NotNull final Context context,
                                      @NotNull final String[] permissions,
                                      @NotNull final Callback callback) {
        withPermission(context, permissions, granted -> {
            if (granted) {
                callback.onGranted();
            }
        });
    }

    public static void withPermission(@NotNull final Context context,
                                      @NotNull final String[] permissions,
                                      @NotNull final AdvancedCallback callback) {
        check(context, permissions, callback);
    }

    @SuppressLint("NewApi")
    private static void check(@NotNull final Context context,
                              @NotNull final String[] permissions,
                              @NotNull final AdvancedCallback callback) {
        if (!needsPermissions()) {
            callback.onGrantedOrDenied(true);
            return;
        }

        final ArrayList<String> permissionsList = new ArrayList<>();
        Collections.addAll(permissionsList, permissions);
        boolean allPermissionProvided = true;
        for (final String aPermission : permissionsList) {
            if (context.checkSelfPermission(aPermission) != PackageManager.PERMISSION_GRANTED) {
                allPermissionProvided = false;
                break;
            }
        }

        if (allPermissionProvided) {
            callback.onGrantedOrDenied(true);
            PermissionsActivity.callback = null;
        } else {
            PermissionsActivity.callback = callback;
            final Intent intent = new Intent(context, PermissionsActivity.class);
            intent.putExtra(PermissionsActivity.EXTRA_PERMISSIONS, permissionsList);
            context.startActivity(intent);
        }
    }

    private static boolean needsPermissions() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}