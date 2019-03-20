package com.sengami.domain_diary.presenter;

import com.sengami.domain_base.operation.Operation;
import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_diary.operation.CreateOrUpdateDiaryEntryOperation;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;
import com.sengami.domain_diary.view.DiaryEntryComposerView;

import org.jetbrains.annotations.NotNull;

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
    protected void onSubscribe(@NotNull final DiaryEntryComposerView view) {
        subscribeSaveDiaryEntryClickedTrigger(view);
        subscribeDeleteDiaryEntryClickedTrigger(view);
        subscribeDateChangedBackTrigger(view);
        subscribeNavigateBackTrigger(view);
    }

    private void subscribeSaveDiaryEntryClickedTrigger(@NotNull final DiaryEntryComposerView view) {
        disposables.add(
            view.getSaveDiaryEntryTrigger()
                .map(createOrUpdateDiaryEntryOperation::withDiaryEntry)
                .flatMap(Operation::execute)
                .subscribe(x -> {
                    view.showOperationSuccessMessage();
                    view.navigateBack();
                })
        );
    }

    private void subscribeDeleteDiaryEntryClickedTrigger(@NotNull final DiaryEntryComposerView view) {
        disposables.add(
            view.getDeleteDiaryEntryTrigger()
                .map(deleteDiaryEntryOperation::withDiaryEntry)
                .flatMap(Operation::execute)
                .subscribe(x -> {
                    view.showOperationSuccessMessage();
                    view.navigateBack();
                })
        );
    }

    private void subscribeDateChangedBackTrigger(@NotNull final DiaryEntryComposerView view) {
        disposables.add(
            view.getDateChangedTrigger()
                .subscribe(view::changeDate)
        );
    }

    private void subscribeNavigateBackTrigger(@NotNull final DiaryEntryComposerView view) {
        disposables.add(
            view.getReturnTrigger()
                .subscribe(x -> view.navigateBack())
        );
    }
}