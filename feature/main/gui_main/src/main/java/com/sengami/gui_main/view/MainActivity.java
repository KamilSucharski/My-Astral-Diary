package com.sengami.gui_main.view;

import com.sengami.domain_main.contract.MainContract;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ErrorHandlerModule;
import com.sengami.gui_base.error.ToastErrorHandler;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_diary.view.DiaryEntryListFragment;
import com.sengami.gui_main.R;
import com.sengami.gui_main.databinding.ActivityMainBinding;
import com.sengami.gui_main.di.component.DaggerMainComponent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends BaseActivity<MainContract.Presenter, ActivityMainBinding> implements MainContract.View {

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
            .build()
            .inject(this);
    }

    @Override
    protected void init() {
        super.init();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new DiaryEntryListFragment());
        fragmentTransaction.commit();
    }
}