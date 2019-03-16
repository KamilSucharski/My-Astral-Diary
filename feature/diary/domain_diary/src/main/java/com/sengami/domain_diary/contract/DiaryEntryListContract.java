package com.sengami.domain_diary.contract;

import com.sengami.domain_base.presenter.ReactivePresenter;
import com.sengami.domain_diary.model.DiaryEntry;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;

public interface DiaryEntryListContract {

    interface View {

        @NotNull
        Observable<DiaryEntry> getDiaryEntryClickedTrigger();

        void showDiaryEntryList(@NotNull final List<DiaryEntry> diaryEntryList);
    }

    interface Presenter extends ReactivePresenter<View> {
    }
}