package com.sengami.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

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

    @NotNull
    private final MessageDialog.Callback callback;
    @NotNull
    private final String message;

    private DialogMessageBinding binding;

    public MessageDialog(@NonNull final Context context,
                         @NotNull final String message,
                         @NotNull final MessageDialog.Callback callback) {
        super(context, R.style.OverlayDialog);
        this.callback = callback;
        this.message = message;
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