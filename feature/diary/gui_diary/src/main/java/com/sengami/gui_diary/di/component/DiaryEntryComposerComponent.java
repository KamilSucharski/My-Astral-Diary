package com.sengami.gui_diary.di.component;

import com.sengami.gui_base.di.module.ConnectionSourceProviderModule;
import com.sengami.gui_base.di.module.ContextModule;
import com.sengami.gui_base.di.module.ReactiveSchedulersModule;
import com.sengami.gui_base.di.module.WithErrorHandlerModule;
import com.sengami.gui_base.di.module.WithLoadingIndicatorModule;
import com.sengami.gui_diary.di.module.MapperModule;
import com.sengami.gui_diary.di.module.OperationModule;
import com.sengami.gui_diary.di.module.PresenterModule;
import com.sengami.gui_diary.view.DiaryEntryComposerActivity;

import dagger.Component;

@Component(modules = {
    ConnectionSourceProviderModule.class,
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