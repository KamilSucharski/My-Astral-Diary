package com.sengami.gui_settings.di.module;

import com.sengami.domain_settings.operation.CreateBackupOperation;
import com.sengami.domain_settings.operation.ExportToTextFileOperation;
import com.sengami.domain_settings.operation.RestoreFromBackupOperation;
import com.sengami.domain_settings.presenter.SettingsPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class PresenterModule {

    @Provides
    @NotNull
    SettingsPresenter diaryEntryListContractPresenter(@NotNull final CreateBackupOperation createBackupOperation,
                                                      @NotNull final RestoreFromBackupOperation restoreFromBackupOperation,
                                                      @NotNull final ExportToTextFileOperation exportToTextFileOperation) {
        return new SettingsPresenter(createBackupOperation, restoreFromBackupOperation, exportToTextFileOperation);
    }
}