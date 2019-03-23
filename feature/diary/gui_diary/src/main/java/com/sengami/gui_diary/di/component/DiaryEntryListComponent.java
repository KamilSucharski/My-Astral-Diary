package com.sengami.gui_diary.di.component;

import com.sengami.android_operation.di.module.ContextModule;
import com.sengami.android_operation.di.module.LoggerModule;
import com.sengami.android_operation.di.module.ReactiveSchedulersModule;
import com.sengami.android_operation.di.module.WithErrorHandlerModule;
import com.sengami.android_operation.di.module.WithLoadingIndicatorModule;
import com.sengami.database_connection.di.module.DatabaseConnectionProviderModule;
import com.sengami.gui_diary.di.module.MapperModule;
import com.sengami.gui_diary.di.module.OperationModule;
import com.sengami.gui_diary.di.module.PresenterModule;
import com.sengami.gui_diary.view.DiaryEntryListFragment;

import dagger.Component;

@Component(modules = {
    ContextModule.class,
    DatabaseConnectionProviderModule.class,
    LoggerModule.class,
    MapperModule.class,
    OperationModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class,
    WithErrorHandlerModule.class,
    WithLoadingIndicatorModule.class,
})
public interface DiaryEntryListComponent {

    void inject(final DiaryEntryListFragment fragment);
}