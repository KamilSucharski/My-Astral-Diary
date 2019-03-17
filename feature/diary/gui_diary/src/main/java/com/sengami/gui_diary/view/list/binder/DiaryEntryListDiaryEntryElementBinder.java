package com.sengami.gui_diary.view.list.binder;

import com.sengami.gui_base.view.list.binder.ViewHolderBinder;
import com.sengami.gui_base.view.list.element.ElementType;
import com.sengami.gui_diary.databinding.ElementDiaryEntryBinding;
import com.sengami.gui_diary.view.list.element.DiaryEntryListDiaryEntryElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElementType;

import org.jetbrains.annotations.NotNull;

public final class DiaryEntryListDiaryEntryElementBinder extends ViewHolderBinder<DiaryEntryListElement, DiaryEntryListDiaryEntryElement, ElementDiaryEntryBinding> {

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return DiaryEntryListElementType.DIARY_ENTRY;
    }

    @Override
    protected final void performBind(@NotNull final ElementDiaryEntryBinding binding,
                                     @NotNull final DiaryEntryListDiaryEntryElement item) {
        binding.setDiaryEntry(item.getDiaryEntry());
    }
}