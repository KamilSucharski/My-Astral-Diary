package com.sengami.recycler_view_adapter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
import com.sengami.recycler_view_adapter.binder.ViewHolderBinder;
import com.sengami.recycler_view_adapter.element.Element;
import com.sengami.recycler_view_adapter.element.ElementType;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<ELEMENT extends Element, TYPE extends ElementType> extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<ELEMENT> items = new LinkedList<>();
    private final TYPE[] possibleElementTypes;
    private final List<ViewHolderBinder<ELEMENT, ? extends ELEMENT, ? extends ViewDataBinding>> viewHolderBinders;

    protected BaseAdapter(@NotNull final TYPE[] possibleElementTypes,
                          @NotNull final List<ViewHolderBinder<ELEMENT, ? extends ELEMENT, ? extends ViewDataBinding>> viewHolderBinders) {
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

        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutRes, parent, false);

        return new BaseViewHolder(binding);
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

    @Override
    public int getItemViewType(int position) {
        final ELEMENT item = items.get(position);
        return item.getType().ordinal();
    }

    @NotNull
    public List<ELEMENT> getItems() {
        return items;
    }

    public void replaceAll(@NotNull final List<ELEMENT> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
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