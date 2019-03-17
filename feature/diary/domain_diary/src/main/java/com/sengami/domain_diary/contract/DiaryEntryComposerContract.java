package com.sengami.domain_diary.contract;

import com.sengami.domain_base.presenter.ReactivePresenter;
import com.sengami.domain_diary.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public interface DiaryEntryComposerContract {

    interface View {

        @NotNull
        Observable<DiaryEntry> getSaveDiaryEntryTrigger();

        @NotNull
        Observable<DiaryEntry> getDeleteDiaryEntryTrigger();

        @NotNull
        Observable<Boolean> getReturnTrigger();

        void showOperationSuccessMessage();

        void navigateBack();
    }

    interface Presenter extends ReactivePresenter<View> {
    }
}