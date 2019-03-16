package com.sengami.gui_base.view.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<ELEMENT extends Element, TYPE extends ElementType> extends RecyclerView.Adapter<BaseViewHolder> {

    private final Context context;
    private final List<ELEMENT> items = new LinkedList<>();
    private final TYPE[] possibleElementTypes;
    private final List<ViewHolderBinder<ELEMENT, ? extends ELEMENT>> viewHolderBinders;

    public BaseAdapter(@NotNull final Context context,
                       @NotNull final TYPE[] possibleElementTypes,
                       @NotNull final List<ViewHolderBinder<ELEMENT, ? extends ELEMENT>> viewHolderBinders) {
        this.context = context;
        this.possibleElementTypes = possibleElementTypes;
        this.viewHolderBinders = viewHolderBinders;
    }

    @NonNull
    @Override
    final public BaseViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                             final int viewType) {
        final int layoutRes = Stream
            .of(possibleElementTypes)
            .filter(type -> type.ordinal() == viewType)
            .findFirst()
            .get()
            .getLayoutRes();

        final View view = LayoutInflater
            .from(context)
            .inflate(layoutRes, parent, false);

        return new BaseViewHolder(view);
    }

    @Override
    final public void onBindViewHolder(@NonNull final BaseViewHolder holder,
                                 final int position) {
        final ELEMENT item = items.get(position);
        Stream
            .of(viewHolderBinders)
            .forEach(binder -> binder.bind(holder, item));
    }

    @Override
    final public int getItemCount() {
        return items.size();
    }

    public void addAll(@NotNull final List<ELEMENT> data) {
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void removeAll() {
        items.clear();
        notifyDataSetChanged();
    }
}