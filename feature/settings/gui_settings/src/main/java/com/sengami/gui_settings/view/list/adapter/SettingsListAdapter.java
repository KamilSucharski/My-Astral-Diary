package com.sengami.gui_settings.view.list.adapter;

import android.content.Context;

import com.sengami.gui_settings.view.list.binder.SettingsListItemElementBinder;
import com.sengami.gui_settings.view.list.binder.SettingsListSectionElementBinder;
import com.sengami.gui_settings.view.list.element.SettingsListElement;
import com.sengami.gui_settings.view.list.element.SettingsListElementType;
import com.sengami.recycler_view_adapter.adapter.BaseAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class SettingsListAdapter extends BaseAdapter<SettingsListElement, SettingsListElementType> {

    public SettingsListAdapter(@NotNull final Context context) {
        super(
            context,
            SettingsListElementType.values(),
            Arrays.asList(
                new SettingsListSectionElementBinder(context),
                new SettingsListItemElementBinder(context)
            )
        );
    }
}