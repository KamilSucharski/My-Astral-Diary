package com.sengami.gui_base.view.list.binder;

import com.sengami.gui_base.view.list.adapter.BaseViewHolder;
import com.sengami.gui_base.view.list.element.Element;
import com.sengami.gui_base.view.list.element.ElementType;

import org.jetbrains.annotations.NotNull;

import androidx.databinding.ViewDataBinding;

public abstract class ViewHolderBinder<ELEMENT extends Element, SPECIFIC_ELEMENT extends ELEMENT, SPECIFIC_DATA_BINDING extends ViewDataBinding> {

    @NotNull
    protected abstract ElementType getAllowedType();

    protected abstract void performBind(@NotNull final SPECIFIC_DATA_BINDING binding,
                                        @NotNull final SPECIFIC_ELEMENT item);

    @SuppressWarnings("unchecked")
    public void bind(@NotNull final BaseViewHolder viewHolder,
                     @NotNull final ELEMENT item) {
        if (shouldBind(item)) {
            performBind((SPECIFIC_DATA_BINDING) viewHolder.getBinding(), (SPECIFIC_ELEMENT) item);
        }
    }

    private boolean shouldBind(@NotNull final ELEMENT item) {
        return item.getType().ordinal() == getAllowedType().ordinal();
    }
}