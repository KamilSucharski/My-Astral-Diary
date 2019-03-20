package com.sengami.domain_diary.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.domain_diary.operation.GetDiaryEntryListOperation;
import com.sengami.domain_diary.view.DiaryEntryListView;

import org.jetbrains.annotations.NotNull;

public final class DiaryEntryListPresenter extends BasePresenter<DiaryEntryListView> {

    @NotNull
    private final GetDiaryEntryListOperation getDiaryEntryListOperation;

    public DiaryEntryListPresenter(@NotNull final GetDiaryEntryListOperation getDiaryEntryListOperation) {
        this.getDiaryEntryListOperation = getDiaryEntryListOperation;
    }

    @Override
    protected void onSubscribe(@NotNull final DiaryEntryListView view) {
        subscribeRefreshListTrigger(view);
        subscribeDiaryEntryClickedTrigger(view);
        subscribeAddNewDiaryEntryClickedTrigger(view);
    }

    private void subscribeRefreshListTrigger(@NotNull final DiaryEntryListView view) {
        disposables.add(
            view.getRefreshListTrigger()
                .flatMap(x -> getDiaryEntryListOperation.execute())
                .subscribe(view::showDiaryEntryList)
        );
    }

    private void subscribeDiaryEntryClickedTrigger(@NotNull final DiaryEntryListView view) {
        disposables.add(
            view.getDiaryEntryClickedTrigger()
                .subscribe(view::navigateToDiaryEntryComposerScreen)
        );
    }

    private void subscribeAddNewDiaryEntryClickedTrigger(@NotNull final DiaryEntryListView view) {
        disposables.add(
            view.getAddNewDiaryClickedEntryTrigger()
                .map(x -> new DiaryEntry())
                .subscribe(view::navigateToDiaryEntryComposerScreen)
        );
    }
}