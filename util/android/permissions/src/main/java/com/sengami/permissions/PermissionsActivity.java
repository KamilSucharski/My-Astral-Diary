package com.sengami.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
@TargetApi(Build.VERSION_CODES.M)
public final class PermissionsActivity extends Activity {

    protected static final String EXTRA_PERMISSIONS = "EXTRA_PERMISSIONS";

    private static final int RC_SETTINGS = 6739;
    private static final int RC_PERMISSION = 6937;

    protected static Permissions.AdvancedCallback callback;
    private boolean cleanHandlerOnDestroy = true;
    private ArrayList<String> allPermissions, deniedPermissions;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtras();

        if (allPermissions == null || allPermissions.isEmpty()) {
            finish();
            return;
        }

        getWindow().setStatusBarColor(0);
        deniedPermissions = new ArrayList<>();

        for (final String permission : allPermissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }

        requestPermissions(deniedPermissions.toArray(new String[0]), RC_PERMISSION);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void onRequestPermissionsResult(final int requestCode,
                                           final String[] permissions,
                                           final int[] grantResults) {
        if (grantResults.length == 0) {
            denyAndFinish();
            return;
        }

        deniedPermissions.clear();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }

        if (deniedPermissions.size() == 0) {
            grantAndFinish();
            return;
        }

        denyAndFinish();
    }

    @Override
    protected void onActivityResult(final int requestCode,
                                    final int resultCode,
                                    final Intent data) {
        if (requestCode == RC_SETTINGS && callback != null) {
            Permissions.withPermission(this,
                    allPermissions.toArray(new String[0]),
                    callback);
            cleanHandlerOnDestroy = false;
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        if (cleanHandlerOnDestroy) {
            callback = null;
        }
        super.onDestroy();
    }

    private void getExtras() {
        final Intent intent = getIntent();
        allPermissions = (ArrayList<String>) intent.getSerializableExtra(EXTRA_PERMISSIONS);
    }

    private void denyAndFinish() {
        if (callback != null) {
            callback.onGrantedOrDenied(false);
        }
        finish();
    }

    private void grantAndFinish() {
        if (callback != null) {
            callback.onGrantedOrDenied(true);
        }
        finish();
    }
}