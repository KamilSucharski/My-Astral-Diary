package com.sengami.domain_diary.presenter;

import com.sengami.domain_base.operation.Operation;
import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_diary.contract.DiaryEntryComposerContract;
import com.sengami.domain_diary.operation.CreateOrUpdateDiaryEntryOperation;
import com.sengami.domain_diary.operation.DeleteDiaryEntryOperation;

import org.jetbrains.annotations.NotNull;

public final class DiaryEntryComposerPresenter extends BasePresenter<DiaryEntryComposerContract.View> implements DiaryEntryComposerContract.Presenter {

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
    protected void onSubscribe(@NotNull final DiaryEntryComposerContract.View view) {
        subscribeSaveDiaryEntryClickedTrigger(view);
        subscribeDeleteDiaryEntryClickedTrigger(view);
        subscribeNavigateBackTrigger(view);
    }

    private void subscribeSaveDiaryEntryClickedTrigger(@NotNull final DiaryEntryComposerContract.View view) {
        disposables.add(
            view
                .getSaveDiaryEntryTrigger()
                .map(createOrUpdateDiaryEntryOperation::withDiaryEntry)
                .flatMap(Operation::execute)
                .subscribe(x -> {
                    view.showOperationSuccessMessage();
                    view.navigateBack();
                })
        );
    }

    private void subscribeDeleteDiaryEntryClickedTrigger(@NotNull final DiaryEntryComposerContract.View view) {
        disposables.add(
            view
                .getDeleteDiaryEntryTrigger()
                .map(deleteDiaryEntryOperation::withDiaryEntry)
                .flatMap(Operation::execute)
                .subscribe(x -> {
                    view.showOperationSuccessMessage();
                    view.navigateBack();
                })
        );
    }

    private void subscribeNavigateBackTrigger(@NotNull final DiaryEntryComposerContract.View view) {
        disposables.add(
            view
                .getReturnTrigger()
                .subscribe(x -> view.navigateBack())
        );
    }
}