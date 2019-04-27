package com.sengami.gui_settings.view;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_settings.view.LicensesView;
import com.sengami.gui_base.view.BaseActivity;
import com.sengami.gui_settings.R;
import com.sengami.gui_settings.databinding.ActivityLicensesBinding;
import com.sengami.gui_settings.di.component.DaggerLicensesComponent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import static com.sengami.clicks.Clicks.onClick;

public final class LicensesActivity
    extends BaseActivity<Presenter<LicensesView>, ActivityLicensesBinding>
    implements LicensesView {

    @Inject
    @Override
    protected void injectPresenter(@NotNull final Presenter<LicensesView> presenter) {
        super.injectPresenter(presenter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_licenses;
    }

    @Override
    protected void inject() {
        DaggerLicensesComponent
            .builder()
            .build()
            .inject(this);
    }

    @Override
    protected void init() {
        super.init();
        binding.setLicenseList(getString(R.string.license_list));
        onClick(binding.backButton, this::finish);
    }
}