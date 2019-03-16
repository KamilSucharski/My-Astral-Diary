package com.sengami.domain_diary.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_diary.contract.DiaryEntryListContract;
import com.sengami.domain_diary.operation.GetDiaryEntryListOperation;

import org.jetbrains.annotations.NotNull;

public final class DiaryEntryListPresenter extends BasePresenter<DiaryEntryListContract.View> implements DiaryEntryListContract.Presenter {

    @NotNull
    private final GetDiaryEntryListOperation getDiaryEntryListOperation;

    public DiaryEntryListPresenter(@NotNull final GetDiaryEntryListOperation getDiaryEntryListOperation) {
        this.getDiaryEntryListOperation = getDiaryEntryListOperation;
    }

    @Override
    protected void onSubscribe(@NotNull final DiaryEntryListContract.View view) {
        subscribeGetDiaryEntryList(view);
        subscribeDiaryEntryClickedTrigger(view);
    }

    private void subscribeGetDiaryEntryList(@NotNull final DiaryEntryListContract.View view) {
        disposables.add(
            getDiaryEntryListOperation
                .execute()
                .subscribe(view::showDiaryEntryList)
        );
    }

    private void subscribeDiaryEntryClickedTrigger(@NotNull final DiaryEntryListContract.View view) {
        //todo
    }
}