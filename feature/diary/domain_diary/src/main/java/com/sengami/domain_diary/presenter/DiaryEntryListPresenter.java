package com.sengami.domain_diary.presenter;

import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_diary.operation.GetDiaryEntriesGroupedByDateOperation;
import com.sengami.domain_diary.view.DiaryEntryListView;

import org.jetbrains.annotations.NotNull;

public final class DiaryEntryListPresenter extends BasePresenter<DiaryEntryListView> {

    @NotNull
    private final GetDiaryEntriesGroupedByDateOperation getDiaryEntriesGroupedByDateOperation;

    public DiaryEntryListPresenter(@NotNull final GetDiaryEntriesGroupedByDateOperation getDiaryEntriesGroupedByDateOperation) {
        this.getDiaryEntriesGroupedByDateOperation = getDiaryEntriesGroupedByDateOperation;
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
                .flatMap(x -> getDiaryEntriesGroupedByDateOperation.execute())
                .subscribe(view::showDiaryEntriesGroupedByDate)
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