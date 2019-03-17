package com.sengami.util_date_picker_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.sengami.util_date_picker_dialog.databinding.DialogDatePickerBinding;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import io.reactivex.subjects.BehaviorSubject;

import static com.sengami.gui_base.util.ClickUtil.onClick;

public class DatePickerDialog extends Dialog {

    @NotNull
    private final LocalDate defaultDate;
    @NotNull
    private final LocalDate minDate;
    @NotNull
    private final LocalDate maxDate;
    @NotNull
    private final BehaviorSubject<LocalDate> onDateEnteredTrigger;

    private DialogDatePickerBinding binding;

    public DatePickerDialog(@NonNull final Context context,
                            @NotNull final LocalDate defaultDate,
                            @NotNull final LocalDate minDate,
                            @NotNull final LocalDate maxDate,
                            @NotNull final BehaviorSubject<LocalDate> onDateEnteredTrigger) {
        super(context, R.style.OverlayDialog);
        this.defaultDate = defaultDate;
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.onDateEnteredTrigger = onDateEnteredTrigger;
    }

    @Override
    protected void onCreate(@NotNull final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setupButtons();
        setupDatePicker();
    }

    private void setupView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_date_picker, null, false);
        setContentView(binding.getRoot());
    }

    private void setupDatePicker() {
        binding.datePicker.setMinDate(minDate.toDate().getTime());
        binding.datePicker.setMaxDate(maxDate.toDate().getTime());

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(defaultDate.toDate());

        binding.datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setupButtons() {
        onClick(binding.buttons.cancelButton, this::dismiss);
        onClick(binding.buttons.acceptButton, () -> {
            onDateEnteredTrigger.onNext(getEnteredDate());
            dismiss();
        });
    }

    private LocalDate getEnteredDate() {
        final int year = binding.datePicker.getYear();
        final int month = binding.datePicker.getMonth();
        final int day = binding.datePicker.getDayOfMonth();

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return LocalDate.fromCalendarFields(calendar);
    }
}