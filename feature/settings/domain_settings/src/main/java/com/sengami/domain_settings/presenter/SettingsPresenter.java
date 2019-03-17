package com.sengami.domain_settings.presenter;

import com.sengami.domain_base.presenter.BasePresenter;
import com.sengami.domain_settings.contract.SettingsContract;

import org.jetbrains.annotations.NotNull;

public final class SettingsPresenter extends BasePresenter<SettingsContract.View> implements SettingsContract.Presenter {

    @Override
    protected void onSubscribe(@NotNull final SettingsContract.View view) {
    }
}