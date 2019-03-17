package com.sengami.gui_diary.view.list.binder;

import com.sengami.domain_diary.model.DiaryEntry;
import com.sengami.gui_base.view.list.binder.ViewHolderBinder;
import com.sengami.gui_base.view.list.element.ElementType;
import com.sengami.gui_diary.databinding.ElementDiaryEntryBinding;
import com.sengami.gui_diary.view.list.element.DiaryEntryListDiaryEntryElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElementType;

import org.jetbrains.annotations.NotNull;

import io.reactivex.subjects.BehaviorSubject;

import static com.sengami.gui_base.util.ClickUtil.onClick;

public final class DiaryEntryListDiaryEntryElementBinder extends ViewHolderBinder<DiaryEntryListElement, DiaryEntryListDiaryEntryElement, ElementDiaryEntryBinding> {

    private final BehaviorSubject<DiaryEntry> diaryEntryClickedTrigger;

    public DiaryEntryListDiaryEntryElementBinder(@NotNull final BehaviorSubject<DiaryEntry> diaryEntryClickedTrigger) {
        this.diaryEntryClickedTrigger = diaryEntryClickedTrigger;
    }

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return DiaryEntryListElementType.DIARY_ENTRY;
    }

    @Override
    protected final void performBind(@NotNull final ElementDiaryEntryBinding binding,
                                     @NotNull final DiaryEntryListDiaryEntryElement item) {
        binding.setDiaryEntry(item.getDiaryEntry());
        onClick(binding, () -> diaryEntryClickedTrigger.onNext(item.getDiaryEntry()));
    }
}