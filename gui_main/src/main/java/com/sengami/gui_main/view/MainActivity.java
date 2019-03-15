package com.sengami.gui_main.view;

import com.sengami.di_base.module.ContextModule;
import com.sengami.di_base.module.ErrorHandlerModule;
import com.sengami.domain_main.contract.MainContract;
import com.sengami.gui_base.error.ToastErrorHandler;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_main.R;
import com.sengami.gui_main.component.DaggerMainComponent;
import com.sengami.gui_main.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends BaseActivity<MainContract.Presenter, ActivityMainBinding> implements MainContract.View  {

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
                .errorHandlerModule(new ErrorHandlerModule(new ToastErrorHandler(this)))
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