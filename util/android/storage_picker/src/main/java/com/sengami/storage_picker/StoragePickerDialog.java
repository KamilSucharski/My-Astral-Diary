package com.sengami.storage_picker;

import android.content.Context;
import android.os.Environment;

import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class StoragePickerDialog {

    @FunctionalInterface
    public interface Callback {
        void onFilePicked(@NotNull final File file);
    }

    @NotNull
    private final Context context;
    @NotNull
    private final Callback callback;
    @NotNull
    private final String[] extensions;

    public StoragePickerDialog(@NotNull final Context context,
                               @NotNull final Callback callback,
                               @NotNull final String... extensions) {
        this.context = context;
        this.callback = callback;
        this.extensions = extensions;
    }

    public void show() {
        final DialogProperties properties = new DialogProperties();
        properties.extensions = extensions;
        properties.root = Environment.getExternalStorageDirectory();
        final FilePickerDialog dialog = new FilePickerDialog(context, properties, R.style.StoragePickerDialog);
        dialog.setDialogSelectionListener(paths -> callback.onFilePicked(new File(paths[0])));
        dialog.show();
    }
}