package com.sengami.gui_settings.view;

import android.content.Context;

import com.sengami.context.di.module.ContextModule;
import com.sengami.domain_settings.presenter.SettingsPresenter;
import com.sengami.domain_settings.view.SettingsView;
import com.sengami.gui_base.BaseFragment;
import com.sengami.gui_settings.R;
import com.sengami.gui_settings.databinding.FragmentSettingsBinding;
import com.sengami.gui_settings.di.component.DaggerSettingsComponent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public final class SettingsFragment
    extends BaseFragment<SettingsPresenter, FragmentSettingsBinding>
    implements SettingsView {

    @Inject
    @Override
    protected void injectPresenter(@NotNull final SettingsPresenter presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void inject(@NotNull final Context context) {
        DaggerSettingsComponent.builder()
            .contextModule(new ContextModule(context))
            .build()
            .inject(this);
    }
}