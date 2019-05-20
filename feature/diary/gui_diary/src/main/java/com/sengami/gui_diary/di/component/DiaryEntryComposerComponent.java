package com.sengami.gui_diary.di.component;

import com.sengami.android_operation.di.module.OperationConfigurationModule;
import com.sengami.gui_base.database.DatabaseConnectionProviderModule;
import com.sengami.gui_diary.di.module.MapperModule;
import com.sengami.gui_diary.di.module.OperationModule;
import com.sengami.gui_diary.di.module.PresenterModule;
import com.sengami.gui_diary.view.DiaryEntryComposerActivity;

import dagger.Component;

@Component(modules = {
    DatabaseConnectionProviderModule.class,
    MapperModule.class,
    OperationModule.class,
    OperationConfigurationModule.class,
    PresenterModule.class,
})
public interface DiaryEntryComposerComponent {

    void inject(final DiaryEntryComposerActivity activity);
}