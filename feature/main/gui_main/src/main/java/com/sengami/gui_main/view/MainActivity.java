package com.sengami.gui_main.view;

import com.sengami.domain_main.contract.MainContract;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_base.view.list.BaseAdapter;
import com.sengami.gui_base.view.list.ElementConverter;
import com.sengami.gui_diary.view.DiaryEntryListFragment;
import com.sengami.gui_main.R;
import com.sengami.gui_main.databinding.ActivityMainBinding;
import com.sengami.gui_main.di.component.DaggerMainComponent;
import com.sengami.gui_main.view.list.MainViewPagerAdapter;
import com.sengami.gui_main.view.list.MainViewPagerElement;
import com.sengami.gui_main.view.list.MainViewPagerElementConverter;
import com.sengami.gui_main.view.list.MainViewPagerElementType;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;

public class MainActivity extends BaseActivity<MainContract.Presenter, ActivityMainBinding> implements MainContract.View {

    private final BaseAdapter<MainViewPagerElement, MainViewPagerElementType> adapter = new MainViewPagerAdapter(this, getSupportFragmentManager());
    private final ElementConverter<List<Fragment>, List<MainViewPagerElement>> converter = new MainViewPagerElementConverter();

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
        final List<MainViewPagerElement> elements = converter.convert(Arrays.asList(
            new DiaryEntryListFragment(),
            new DiaryEntryListFragment(),
            new DiaryEntryListFragment()
        ));
        adapter.addAll(elements);
        binding.viewPager.setAdapter(adapter);
    }
}