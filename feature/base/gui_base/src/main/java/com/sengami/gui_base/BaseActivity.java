package com.sengami.gui_base;

import android.content.Intent;
import android.os.Bundle;

import com.sengami.domain_base.presenter.ReactivePresenter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<P extends ReactivePresenter, DB extends ViewDataBinding> extends AppCompatActivity {

    protected P presenter;
    protected DB binding;

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract void inject();

    @SuppressWarnings("unchecked")
    protected void injectPresenter(@NotNull final P presenter) {
        this.presenter = presenter;
    }

    @Override
    protected final void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        extractArguments(getIntent());
        bindLayout();
        inject();
        init();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onStart() {
        super.onStart();
        presenter.subscribe(this);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onStop() {
        presenter.unsubscribe();
        super.onStop();
    }

    protected void extractArguments(@NotNull final Intent intent) {
    }

    protected void init() {
    }

    protected void startActivity(@NotNull final Class<?> activity) {
        final Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    protected void startActivity(@NotNull final Class<?> activity,
                                 @NotNull final Bundle extras) {
        final Intent intent = new Intent(this, activity);
        intent.putExtras(extras);
        startActivity(intent);
    }

    protected void changeActivity(@NotNull final Class<?> activity) {
        finish();
        startActivity(activity);
    }

    protected void changeActivity(@NotNull final Class<?> activity,
                                  @NotNull final Bundle extras) {
        startActivity(activity, extras);
        finish();
    }

    private void bindLayout() {
        binding = DataBindingUtil.setContentView(this, getLayoutResource());
    }
}