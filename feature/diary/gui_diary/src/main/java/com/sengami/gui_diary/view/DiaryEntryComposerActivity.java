package com.sengami.gui_diary.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.sengami.android_operation.di.module.ContextModule;
import com.sengami.android_operation.di.module.WithErrorHandlerModule;
import com.sengami.android_operation.di.module.WithLoadingIndicatorModule;
import com.sengami.android_operation.implementation.ToastErrorHandler;
import com.sengami.android_operation.implementation.ViewVisibilityLoadingIndicator;
import com.sengami.dialogs.date.DatePickerDialog;
import com.sengami.dialogs.message.MessageDialog;
import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.operation.error.ErrorHandler;
import com.sengami.domain_base.operation.loading.LoadingIndicator;
import com.sengami.domain_diary.presenter.DiaryEntryComposerPresenter;
import com.sengami.domain_diary.view.DiaryEntryComposerView;
import com.sengami.gui_base.navigation.Extra;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_diary.R;
import com.sengami.gui_diary.databinding.ActivityDiaryEntryComposerBinding;
import com.sengami.gui_diary.di.component.DaggerDiaryEntryComposerComponent;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import static com.sengami.clicks.Clicks.onClick;

public final class DiaryEntryComposerActivity
    extends BaseActivity<DiaryEntryComposerPresenter, ActivityDiaryEntryComposerBinding>
    implements DiaryEntryComposerView {

    private final Subject<DiaryEntry> saveDiaryEntryTrigger = PublishSubject.create();
    private final Subject<DiaryEntry> deleteDiaryEntryTrigger = PublishSubject.create();
    private final Subject<LocalDate> dateChangedTrigger = PublishSubject.create();
    private final Subject<Boolean> returnTrigger = PublishSubject.create();

    private DiaryEntry diaryEntry;

    @Inject
    @Override
    protected void injectPresenter(@NotNull final DiaryEntryComposerPresenter presenter) {
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
        onClick(binding.bottomMenu.deleteButton, this::onDeleteDiaryEntryClicked);
        onClick(binding.bottomMenu.backButton, this::onBackPressed);
    }

    private void onDateButtonClicked() {
        final LocalDate defaultDate = diaryEntry.getDate();
        final LocalDate minDate = LocalDate.now().minusYears(100);
        final LocalDate maxDate = LocalDate.now();
        new DatePickerDialog(this, defaultDate, minDate, maxDate, dateChangedTrigger::onNext).show();
    }

    private void onSaveButtonClicked() {
        saveDiaryEntryTrigger.onNext(diaryEntry);
    }

    private void onDeleteDiaryEntryClicked() {
        new MessageDialog(
            this,
            getString(R.string.delete_diary_entry_warning),
            () -> deleteDiaryEntryTrigger.onNext(diaryEntry)
        ).show();
    }

    private void updateDiaryEntryOnView() {
        binding.setDiaryEntry(diaryEntry);
    }
}