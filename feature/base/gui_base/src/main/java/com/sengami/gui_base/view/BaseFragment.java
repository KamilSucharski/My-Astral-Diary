package com.sengami.gui_base.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sengami.domain_base.presenter.ReactivePresenter;
import com.sengami.gui_base.runnable.TypedRunnable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends ReactivePresenter, DB extends ViewDataBinding> extends Fragment {

    protected P presenter;
    protected DB binding;

    @SuppressWarnings("unchecked")
    protected void injectPresenter(@NotNull final P presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        extractArguments(getArguments());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(@NotNull final Context context) {
        super.onAttach(context);
        inject(context);
        presenter.subscribe(this);
    }

    @NotNull
    @Override
    public View onCreateView(@NotNull final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NotNull final View view,
                              @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = view.getContext();
        init(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onDetach() {
        presenter.unsubscribe();
        super.onDetach();
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract void inject(@NotNull final Context context);

    protected void extractArguments(@Nullable final Bundle arguments) {
    }

    protected void init(@NotNull final Context context) {
    }

    protected void onClick(@NotNull final View view,
                           @NotNull final Runnable runnable) {
        view.setOnClickListener(v -> runnable.run());
    }

    protected void onClick(@NotNull final ViewDataBinding viewDataBinding,
                           @NotNull final Runnable runnable) {
        onClick(viewDataBinding.getRoot(), runnable);
    }

    protected void withContext(@NotNull final TypedRunnable<Context> contextRunnable) {
        final Context context = getActivity();
        if (context == null) {
            return;
        }

        contextRunnable.invoke(context);
    }
}