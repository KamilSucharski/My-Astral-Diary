package com.sengami.gui_settings.view.list.converter;

import com.sengami.gui_settings.R;
import com.sengami.gui_settings.view.list.element.SettingsListElement;
import com.sengami.gui_settings.view.list.element.SettingsListItemElement;
import com.sengami.gui_settings.view.list.element.SettingsListSectionElement;
import com.sengami.recycler_view_adapter.converter.ElementConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class SettingsListElementConverter implements ElementConverter<SettingsListElementConverterPayload, SettingsListElement> {

    @NotNull
    @Override
    public List<SettingsListElement> convert(@NotNull final SettingsListElementConverterPayload payload) {
        return Arrays.asList(
            new SettingsListSectionElement(R.string.settings_section_backup),
            new SettingsListItemElement(R.string.settings_item_backup_create, payload.getOnCreateBackupTrigger()),
            new SettingsListItemElement(R.string.settings_item_backup_restore, payload.getOnRestoreBackupTrigger()),
            new SettingsListSectionElement(R.string.settings_section_export),
            new SettingsListItemElement(R.string.settings_item_export_to_text_file, payload.getOnExportToTextFileTrigger())
        );
    }
}