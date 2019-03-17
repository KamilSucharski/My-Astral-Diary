package com.sengami.gui_settings.di.module;

import com.sengami.domain_settings.contract.SettingsContract;
import com.sengami.domain_settings.presenter.SettingsPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    SettingsContract.Presenter diaryEntryListContractPresenter() {
        return new SettingsPresenter();
    }
}