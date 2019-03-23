package com.sengami.dialogs.file;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;

import com.annimon.stream.Stream;
import com.sengami.dialogs.R;
import com.sengami.dialogs.databinding.DialogFilePickerBinding;
import com.sengami.dialogs.file.list.adapter.FileListAdapter;
import com.sengami.dialogs.file.list.adapter.FileListCallbacks;
import com.sengami.dialogs.file.list.converter.FileListElementConverter;
import com.sengami.dialogs.file.list.element.FileListElement;
import com.sengami.dialogs.file.list.element.FileListFileElement;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
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

    public static final File ROOT = Environment.getExternalStorageDirectory();

    @NotNull
    private final Callback callback;
    private final ElementConverter<File, FileListElement> converter;
    private FileListAdapter adapter;

    @Nullable
    private File selectedFile = null;
    private DialogFilePickerBinding binding;

    public FilePickerDialog(@NonNull final Context context,
                            @NotNull final Callback callback,
                            @NotNull final String... allowedExtensions) {
        super(context, R.style.OverlayDialog);
        this.callback = callback;
        this.converter = new FileListElementConverter(allowedExtensions);
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
        Stream.of(adapter.getItems())
            .filter(element -> element instanceof FileListFileElement)
            .map(element -> (FileListFileElement) element)
            .forEach(element -> element.setSelected(element.getFile().equals(selectedFile)));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDirectoryClicked(@NotNull final File file) {
        selectedFile = null;
        binding.buttons.setAcceptButtonDisabled(true);
        updateList(file);
    }

    private void setupView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_file_picker, null, false);
        setContentView(binding.getRoot());
    }

    private void setupList() {
        adapter = new FileListAdapter(getContext(), this);
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
        onDirectoryClicked(ROOT);
    }

    private void updateList(@NotNull final File rootFile) {
        final List<FileListElement> elements = converter.convert(rootFile);
        adapter.replaceAll(elements);
    }
}