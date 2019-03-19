package com.sengami.gui_diary.view.list.binder;

import com.sengami.gui_diary.databinding.ElementDateHeaderBinding;
import com.sengami.gui_diary.view.list.element.DiaryEntryListDateHeaderElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElementType;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.ElementType;

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