package com.sengami.dialogs.file;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;

import com.sengami.dialogs.R;
import com.sengami.dialogs.databinding.DialogFilePickerBinding;
import com.sengami.dialogs.file.list.adapter.FileListAdapter;
import com.sengami.dialogs.file.list.adapter.FileListCallbacks;
import com.sengami.dialogs.file.list.converter.FileListElementConverter;
import com.sengami.dialogs.file.list.element.FileListElement;
import com.sengami.dialogs.file.list.element.FileListElementType;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.sengami.clicks.Clicks.onClick;

public class FilePickerDialog extends Dialog implements FileListCallbacks {

    @FunctionalInterface
    public interface Callback {
        void onFilePicked(@NotNull final File file);
    }

    @NotNull
    private final Callback callback;
    private BaseAdapter<FileListElement, FileListElementType> adapter;
    private ElementConverter<List<File>, FileListElement> converter;

    @Nullable
    private File selectedFile = null;
    private DialogFilePickerBinding binding;

    public FilePickerDialog(@NonNull final Context context,
                            @NotNull final Callback callback,
                            @NotNull final String allowedExtension) {
        super(context, R.style.OverlayDialog);
        this.callback = callback;
    }

    @Override
    protected void onCreate(@NotNull final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setupList();
        setupButtons();
        loadInitialListData();
    }

    @Override
    public void onFileClicked(@NotNull final File file) {
        selectedFile = file;
        binding.buttons.setAcceptButtonDisabled(false);
    }

    @Override
    public void onDirectoryClicked(@NotNull final File file) {
        updateList(Arrays.asList(file.listFiles()));
        binding.buttons.setAcceptButtonDisabled(true);
    }

    private void setupView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_file_picker, null, false);
        setContentView(binding.getRoot());
    }

    private void setupList() {
        adapter = new FileListAdapter(this);
        converter = new FileListElementConverter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupButtons() {
        onClick(binding.buttons.cancelButton, this::dismiss);
        onClick(binding.buttons.acceptButton, () -> {
            if (selectedFile != null) {
                callback.onFilePicked(selectedFile);
            }
            dismiss();
        });
    }

    private void loadInitialListData() {
        onDirectoryClicked(Environment.getExternalStorageDirectory());
    }

    private void updateList(@NotNull final List<File> fileList) {
        final List<FileListElement> elements = converter.convert(fileList);
        adapter.replaceAll(elements);
    }
}