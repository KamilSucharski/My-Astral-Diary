package com.sengami.gui_diary.view.list.binder;

import com.sengami.domain_base.model.DiaryEntry;
import com.sengami.gui_diary.databinding.ElementDiaryEntryBinding;
import com.sengami.gui_diary.view.list.element.DiaryEntryListDiaryEntryElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElementType;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;

import static com.sengami.clicks.Clicks.onClick;

public final class DiaryEntryListDiaryEntryElementBinder extends ViewHolderBinder<DiaryEntryListElement, DiaryEntryListDiaryEntryElement, ElementDiaryEntryBinding> {

    private final Observer<DiaryEntry> diaryEntryClickedTrigger;

    public DiaryEntryListDiaryEntryElementBinder(@NotNull final Observer<DiaryEntry> diaryEntryClickedTrigger) {
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