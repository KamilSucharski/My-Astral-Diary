package com.sengami.gui_base.view.list;

import org.jetbrains.annotations.NotNull;

public abstract class ViewHolderBinder<ELEMENT extends Element, SPECIFIC_ELEMENT extends ELEMENT> {

    protected abstract int getTypeOrdinal();

    protected abstract void performBind(@NotNull final BaseViewHolder viewHolder,
                                        @NotNull final SPECIFIC_ELEMENT item);

    @SuppressWarnings("unchecked")
    public void bind(@NotNull final BaseViewHolder viewHolder,
                     @NotNull final ELEMENT item) {
        if (shouldBind(item)) {
            performBind(viewHolder, (SPECIFIC_ELEMENT) item);
        }
    }

    private boolean shouldBind(@NotNull final ELEMENT item) {
        return item.getType().ordinal() == getTypeOrdinal();
    }
}