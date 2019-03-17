package com.sengami.gui_diary.view.list.binder;

import com.sengami.gui_base.view.list.binder.ViewHolderBinder;
import com.sengami.gui_base.view.list.element.ElementType;
import com.sengami.gui_diary.databinding.ElementDateHeaderBinding;
import com.sengami.gui_diary.view.list.element.DiaryEntryListDateHeaderElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElementType;

import org.jetbrains.annotations.NotNull;

public final class DiaryEntryListDateHeaderElementBinder extends ViewHolderBinder<DiaryEntryListElement, DiaryEntryListDateHeaderElement, ElementDateHeaderBinding> {

    @Override
    @NotNull
    protected final ElementType getAllowedType() {
        return DiaryEntryListElementType.DATE_HEADER;
    }

    @Override
    protected final void performBind(@NotNull final ElementDateHeaderBinding binding,
                                     @NotNull final DiaryEntryListDateHeaderElement item) {
        binding.setDate(item.getDate());
    }
}