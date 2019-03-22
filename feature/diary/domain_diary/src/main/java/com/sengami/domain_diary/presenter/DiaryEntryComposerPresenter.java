package com.sengami.domain_diary.presenter;

import com.sengami.domain_base.operation.Operation;
import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_diary.operation.CreateOrUpdateDiaryEntryOperation;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;
import com.sengami.domain_diary.view.DiaryEntryComposerView;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public final class DiaryEntryComposerPresenter extends BasePresenter<DiaryEntryComposerView> {

    @NotNull
    private final CreateOrUpdateDiaryEntryOperation createOrUpdateDiaryEntryOperation;

    @NotNull
    private final DeleteDiaryEntryOperation deleteDiaryEntryOperation;

    public DiaryEntryComposerPresenter(@NotNull final CreateOrUpdateDiaryEntryOperation createOrUpdateDiaryEntryOperation,
                                       @NotNull final DeleteDiaryEntryOperation deleteDiaryEntryOperation) {
        this.createOrUpdateDiaryEntryOperation = createOrUpdateDiaryEntryOperation;
        this.deleteDiaryEntryOperation = deleteDiaryEntryOperation;
    }

    @Override
    protected List<Disposable> createSubscriptions(@NotNull final DiaryEntryComposerView view) {
        return Arrays.asList(
            subscribeSaveDiaryEntryClickedTrigger(view),
            subscribeDeleteDiaryEntryClickedTrigger(view),
            subscribeDateChangedBackTrigger(view),
            subscribeNavigateBackTrigger(view)
        );
    }

    private Disposable subscribeSaveDiaryEntryClickedTrigger(@NotNull final DiaryEntryComposerView view) {
        return view
            .getSaveDiaryEntryTrigger()
            .map(createOrUpdateDiaryEntryOperation::withDiaryEntry)
            .flatMap(Operation::execute)
            .subscribe(x -> {
                view.showOperationSuccessMessage();
                view.navigateBack();
            });
    }

    private Disposable subscribeDeleteDiaryEntryClickedTrigger(@NotNull final DiaryEntryComposerView view) {
        return view
            .getDeleteDiaryEntryTrigger()
            .map(deleteDiaryEntryOperation::withDiaryEntry)
            .flatMap(Operation::execute)
            .subscribe(x -> {
                view.showOperationSuccessMessage();
                view.navigateBack();
            });
    }

    private Disposable subscribeDateChangedBackTrigger(@NotNull final DiaryEntryComposerView view) {
        return view
            .getDateChangedTrigger()
            .subscribe(view::changeDate);
    }

    private Disposable subscribeNavigateBackTrigger(@NotNull final DiaryEntryComposerView view) {
        return view
            .getReturnTrigger()
            .subscribe(x -> view.navigateBack());
    }
}