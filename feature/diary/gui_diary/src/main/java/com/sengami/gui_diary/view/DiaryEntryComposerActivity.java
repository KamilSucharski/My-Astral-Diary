package com.sengami.gui_diary.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.sengami.domain_diary.contract.DiaryEntryComposerContract;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_diary.R;
import com.sengami.gui_diary.databinding.ActivityDiaryEntryComposerBinding;
import com.sengami.gui_diary.di.component.DaggerDiaryComponent;
import com.sengami.gui_diary.navigation.Extra;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class DiaryEntryComposerActivity extends BaseActivity<DiaryEntryComposerContract.Presenter, ActivityDiaryEntryComposerBinding> implements DiaryEntryComposerContract.View {

    private final BehaviorSubject<DiaryEntry> saveDiaryEntryTrigger = BehaviorSubject.create();
    private final BehaviorSubject<DiaryEntry> deleteDiaryEntryTrigger = BehaviorSubject.create();
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
        DaggerDiaryComponent.builder()
            .contextModule(new ContextModule(this))
            .build()
            .inject(this);
    }

    @Override
    protected void init() {
        super.init();
        setupListeners();
        showDiaryEntryOnView();
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
    public void showOperationSuccessMessage() {
        Toast.makeText(this, R.string.operation_successful, Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK);
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        returnTrigger.onNext(true);
    }

    private void setupListeners() {
        onClick(binding.bottomMenu.saveButton, () -> saveDiaryEntryTrigger.onNext(diaryEntry));
        onClick(binding.bottomMenu.deleteButton, () -> deleteDiaryEntryTrigger.onNext(diaryEntry));
        onClick(binding.bottomMenu.backButton, () -> returnTrigger.onNext(true));
    }

    private void showDiaryEntryOnView() {
        binding.setDiaryEntry(diaryEntry);
    }
}