package com.sengami.gui_diary.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.sengami.domain_base.util.error.ErrorHandler;
import com.sengami.domain_base.util.error.WithErrorHandler;
import com.sengami.domain_base.util.loading.LoadingIndicator;
import com.sengami.domain_base.util.loading.WithLoadingIndicator;
import com.sengami.domain_diary.contract.DiaryEntryComposerContract;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.WithErrorHandlerModule;
import com.sengami.gui_base.di.module.WithLoadingIndicatorModule;
import com.sengami.gui_base.util.error.ToastErrorHandler;
import com.sengami.gui_base.util.loading.ViewVisibilityLoadingIndicator;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_diary.R;
import com.sengami.gui_diary.databinding.ActivityDiaryEntryComposerBinding;
import com.sengami.gui_diary.di.component.DaggerDiaryEntryComposerComponent;
import com.sengami.gui_diary.navigation.Extra;
import com.sengami.util_date_picker_dialog.DatePickerDialog;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

import static com.sengami.gui_base.util.ClickUtil.onClick;

public final class DiaryEntryComposerActivity
    extends BaseActivity<DiaryEntryComposerContract.Presenter, ActivityDiaryEntryComposerBinding>
    implements DiaryEntryComposerContract.View, WithErrorHandler, WithLoadingIndicator {

    private final BehaviorSubject<DiaryEntry> saveDiaryEntryTrigger = BehaviorSubject.create();
    private final BehaviorSubject<DiaryEntry> deleteDiaryEntryTrigger = BehaviorSubject.create();
    private final BehaviorSubject<LocalDate> dateChangedTrigger = BehaviorSubject.create();
    private final BehaviorSubject<Boolean> returnTrigger = BehaviorSubject.create();

    private DiaryEntry diaryEntry;

    @Inject
    @Override
    protected void injectPresenter(@NotNull final DiaryEntryComposerContract.Presenter presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_diary_entry_composer;
    }

    @Override
    protected void extractArguments(@NotNull final Intent intent) {
        super.extractArguments(intent);
        diaryEntry = (DiaryEntry) intent.getSerializableExtra(Extra.DIARY_ENTRY.name());
    }

    @Override
    protected void inject() {
        DaggerDiaryEntryComposerComponent.builder()
            .contextModule(new ContextModule(this))
            .withErrorHandlerModule(new WithErrorHandlerModule(this))
            .withLoadingIndicatorModule(new WithLoadingIndicatorModule(this))
            .build()
            .inject(this);
    }

    @Override
    protected void init() {
        super.init();
        setupListeners();
        updateDiaryEntryOnView();
    }

    @Override
    @NotNull
    public Observable<DiaryEntry> getSaveDiaryEntryTrigger() {
        return saveDiaryEntryTrigger;
    }

    @Override
    @NotNull
    public Observable<DiaryEntry> getDeleteDiaryEntryTrigger() {
        return deleteDiaryEntryTrigger;
    }

    @Override
    @NotNull
    public Observable<Boolean> getReturnTrigger() {
        return returnTrigger;
    }

    @Override
    @NotNull
    public Observable<LocalDate> getDateChangedTrigger() {
        return dateChangedTrigger;
    }

    @Override
    public void showOperationSuccessMessage() {
        Toast.makeText(this, R.string.operation_successful, Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK);
    }

    @Override
    public void changeDate(@NotNull final LocalDate localDate) {
        diaryEntry.setDate(localDate);
        updateDiaryEntryOnView();
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        returnTrigger.onNext(true);
    }

    @Override
    @NotNull
    public ErrorHandler getErrorHandler() {
        return new ToastErrorHandler(this);
    }

    @Override
    @NotNull
    public LoadingIndicator getLoadingIndicator() {
        return new ViewVisibilityLoadingIndicator(binding.loadingWheelOverlay);
    }

    private void setupListeners() {
        onClick(binding.dateTextView, this::onDateButtonClicked);
        onClick(binding.bottomMenu.saveButton, this::onSaveButtonClicked);
        onClick(binding.bottomMenu.deleteButton, () -> deleteDiaryEntryTrigger.onNext(diaryEntry));
        onClick(binding.bottomMenu.backButton, () -> returnTrigger.onNext(true));
    }

    private void onDateButtonClicked() {
        final LocalDate defaultDate = diaryEntry.getDate();
        final LocalDate minDate = LocalDate.now().minusYears(100);
        final LocalDate maxDate = LocalDate.now();
        new DatePickerDialog(this, defaultDate, minDate, maxDate, dateChangedTrigger).show();
    }

    private void onSaveButtonClicked() {
        saveDiaryEntryTrigger.onNext(diaryEntry);
    }

    private void updateDiaryEntryOnView() {
        binding.setDiaryEntry(diaryEntry);
    }
}