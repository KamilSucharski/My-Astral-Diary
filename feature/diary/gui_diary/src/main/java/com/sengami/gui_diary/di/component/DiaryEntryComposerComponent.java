package com.sengami.gui_diary.di.component;

import com.sengami.database_connection.di.module.DatabaseConnectionProviderModule;
import com.sengami.error_handler.di.module.WithErrorHandlerModule;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ReactiveSchedulersModule;
import com.sengami.gui_diary.di.module.MapperModule;
import com.sengami.gui_diary.di.module.OperationModule;
import com.sengami.gui_diary.di.module.PresenterModule;
import com.sengami.gui_diary.view.DiaryEntryComposerActivity;
import com.sengami.util_loading_indicator.di.module.WithLoadingIndicatorModule;

import dagger.Component;

@Component(modules = {
    DatabaseConnectionProviderModule.class,
    ContextModule.class,
    WithErrorHandlerModule.class,
    WithLoadingIndicatorModule.class,
    MapperModule.class,
    OperationModule.class,
    PresenterModule.class,
    ReactiveSchedulersModule.class
})
public interface DiaryEntryComposerComponent {

    void inject(final DiaryEntryComposerActivity activity);
}