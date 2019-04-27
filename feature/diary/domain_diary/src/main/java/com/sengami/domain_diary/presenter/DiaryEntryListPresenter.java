package com.sengami.domain_diary.presenter;

import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_diary.operation.GetDiaryEntriesOperation;
import com.sengami.domain_diary.view.DiaryEntryListView;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public final class DiaryEntryListPresenter extends BasePresenter<DiaryEntryListView> {

    @NotNull
    private final GetDiaryEntriesOperation getDiaryEntriesOperation;

    public DiaryEntryListPresenter(@NotNull final GetDiaryEntriesOperation getDiaryEntriesOperation) {
        this.getDiaryEntriesOperation = getDiaryEntriesOperation;
    }

    @Override
    protected List<Disposable> createSubscriptions(@NotNull final DiaryEntryListView view) {
        return Arrays.asList(
            subscribeRefreshListTrigger(view),
            subscribeDiaryEntryClickedTrigger(view),
            subscribeAddNewDiaryEntryClickedTrigger(view)
        );
    }

    private Disposable subscribeRefreshListTrigger(@NotNull final DiaryEntryListView view) {
        return view
            .getRefreshListTrigger()
            .flatMap(x -> getDiaryEntriesOperation.execute())
            .subscribe(view::showDiaryEntries);
    }

    private Disposable subscribeDiaryEntryClickedTrigger(@NotNull final DiaryEntryListView view) {
        return view
            .getDiaryEntryClickedTrigger()
            .subscribe(view::navigateToDiaryEntryComposerScreen);
    }

    private Disposable subscribeAddNewDiaryEntryClickedTrigger(@NotNull final DiaryEntryListView view) {
        return view
            .getAddNewDiaryClickedEntryTrigger()
            .map(x -> new DiaryEntry())
            .subscribe(view::navigateToDiaryEntryComposerScreen);
    }
}