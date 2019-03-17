package com.sengami.gui_statistics.view;

import android.content.Context;

import com.sengami.domain_statistics.contract.StatisticsContract;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.view.BaseFragment;
import com.sengami.gui_statistics.R;
import com.sengami.gui_statistics.databinding.FragmentStatisticsBinding;
import com.sengami.gui_statistics.di.component.DaggerStatisticsComponent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public final class StatisticsFragment extends BaseFragment<StatisticsContract.Presenter, FragmentStatisticsBinding> implements StatisticsContract.View {

    @Inject
    @Override
    protected void injectPresenter(@NotNull final StatisticsContract.Presenter presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_statistics;
    }

    @Override
    protected void inject(@NotNull final Context context) {
        DaggerStatisticsComponent.builder()
            .contextModule(new ContextModule(context))
            .build()
            .inject(this);
    }
}