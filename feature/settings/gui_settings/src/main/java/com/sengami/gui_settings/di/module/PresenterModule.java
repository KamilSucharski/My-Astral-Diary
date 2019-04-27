package com.sengami.gui_settings.di.module;

import com.sengami.domain_base.presenter.Presenter;
import com.sengami.domain_settings.operation.CreateBackupOperation;
import com.sengami.domain_settings.operation.ExportToTextFileOperation;
import com.sengami.domain_settings.operation.RestoreFromBackupOperation;
import com.sengami.domain_settings.presenter.LicensesPresenter;
import com.sengami.domain_settings.presenter.SettingsPresenter;
import com.sengami.domain_settings.view.LicensesView;
import com.sengami.domain_settings.view.SettingsView;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    Presenter<SettingsView> diaryEntryListContractPresenter(@NotNull final CreateBackupOperation createBackupOperation,
                                                            @NotNull final RestoreFromBackupOperation restoreFromBackupOperation,
                                                            @NotNull final ExportToTextFileOperation exportToTextFileOperation) {
        return new SettingsPresenter(createBackupOperation, restoreFromBackupOperation, exportToTextFileOperation);
    }

    @Provides
    @NotNull
    Presenter<LicensesView> licensesViewPresenter() {
        return new LicensesPresenter();
    }
}