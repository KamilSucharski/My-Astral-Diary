package com.sengami.recycler_view_adapter.binder;

import com.sengami.recycler_view_adapter.adapter.BaseViewHolder;
import com.sengami.recycler_view_adapter.element.Element;
import com.sengami.recycler_view_adapter.element.ElementType;

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