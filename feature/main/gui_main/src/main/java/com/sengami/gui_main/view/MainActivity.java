package com.sengami.gui_main.view;

import com.sengami.domain_main.contract.MainContract;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ErrorHandlerModule;
import com.sengami.gui_base.error.ToastErrorHandler;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_main.R;
import com.sengami.gui_main.databinding.ActivityMainBinding;
import com.sengami.gui_main.di.component.DaggerMainComponent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends BaseActivity<MainContract.Presenter, ActivityMainBinding> implements MainContract.View {

    private final BehaviorSubject<Boolean> helloButtonTrigger = BehaviorSubject.create();

    @Inject
    @Override
    protected void injectPresenter(@NotNull final MainContract.Presenter presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
        DaggerMainComponent.builder()
            .contextModule(new ContextModule(this))
            .build()
            .inject(this);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        onClick(binding.clickMeButton, () -> helloButtonTrigger.onNext(true));
    }

    @Override
    @NotNull
    public Observable<Boolean> getHelloButtonTrigger() {
        return helloButtonTrigger;
    }

    @Override
    public void showHelloWorld(String text) {
        binding.helloWorldTextView.setText(text);
    }
}