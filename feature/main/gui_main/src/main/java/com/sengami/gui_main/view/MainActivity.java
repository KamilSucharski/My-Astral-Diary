package com.sengami.gui_main.view;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_main.view.MainView;
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

public final class MainActivity
    extends BaseActivity<Presenter<MainView>, ActivityMainBinding>
    implements MainView {

    @Inject
    @Override
    protected void injectPresenter(@NotNull final Presenter<MainView> presenter) {
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
        binding.viewPager.setOffscreenPageLimit(elements.size() - 1);
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}