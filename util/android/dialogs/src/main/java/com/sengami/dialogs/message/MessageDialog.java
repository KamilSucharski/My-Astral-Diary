package com.sengami.dialogs.message;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.sengami.dialogs.R;
import com.sengami.dialogs.databinding.DialogMessageBinding;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import static com.sengami.clicks.Clicks.onClick;

public class MessageDialog extends Dialog {

    @FunctionalInterface
    public interface Callback {
        void onAcceptClicked();
    }

    @NonNull
    private final String title;
    @NotNull
    private final String message;
    @NotNull
    private final MessageDialog.Callback callback;

    private DialogMessageBinding binding;

    public MessageDialog(@NonNull final Context context,
                         @NotNull final String message,
                         @NotNull final MessageDialog.Callback callback) {
        this(context, context.getString(R.string.message_dialog_default_title), message, callback);
    }

    public MessageDialog(@NonNull final Context context,
                         @NotNull final String title,
                         @NotNull final String message,
                         @NotNull final MessageDialog.Callback callback) {
        super(context, R.style.OverlayDialog);
        this.title = title;
        this.message = message;
        this.callback = callback;
    }

    @Override
    protected void onCreate(@NotNull final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setupButtons();
    }

    private void setupView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_message, null, false);
        setContentView(binding.getRoot());
        binding.setTitle(title);
        binding.setMessage(message);
    }

    private void setupButtons() {
        onClick(binding.buttons.cancelButton, this::dismiss);
        onClick(binding.buttons.acceptButton, () -> {
            callback.onAcceptClicked();
            dismiss();
        });
    }
}