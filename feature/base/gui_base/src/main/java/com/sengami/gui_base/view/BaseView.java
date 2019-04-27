package com.sengami.gui_base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.sengami.domain_base.presenter.Presenter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseView<P extends Presenter, DB extends ViewDataBinding> extends FrameLayout {

    private P presenter;
    protected DB binding;

    @SuppressWarnings("unchecked")
    protected void injectPresenter(@NotNull final P presenter) {
        this.presenter = presenter;
    }

    public BaseView(@NotNull final Context context) {
        this(context, null);
    }

    public BaseView(@NotNull final Context context,
                    @Nullable final AttributeSet attrs) {
        super(context, attrs);
        inflate(context, getLayoutResource(), this);
        binding = DataBindingUtil.bind(getLayoutBindingRoot());
        inject(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.subscribe(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.unsubscribe();
        super.onDetachedFromWindow();
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract View getLayoutBindingRoot();

    protected abstract void inject(@NotNull final Context context);

    protected void onSubscribed() {
    }
}
