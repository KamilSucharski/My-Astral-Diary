package com.sengami.gui_main.view;

import com.sengami.domain_main.contract.MainContract;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_main.R;
import com.sengami.gui_main.databinding.ActivityMainBinding;
import com.sengami.gui_main.di.component.DaggerMainComponent;
import com.sengami.gui_main.view.pager.MainViewPagerAdapter;
import com.sengami.gui_main.view.pager.MainViewPagerElement;
import com.sengami.gui_main.view.pager.MainViewPagerElementFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

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
        setupViewPager();
    }

    private void setupViewPager() {
        final List<MainViewPagerElement> elements = new MainViewPagerElementFactory().create(this);
        final MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), elements);
        binding.viewPager.setOffscreenPageLimit(2);
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}