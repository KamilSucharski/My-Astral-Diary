package com.sengami.gui_diary.view.list.adapter;

import android.content.Context;

import com.sengami.gui_base.view.list.adapter.BaseAdapter;
import com.sengami.gui_diary.view.list.binder.DiaryEntryListDateHeaderElementBinder;
import com.sengami.gui_diary.view.list.binder.DiaryEntryListDiaryEntryElementBinder;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElement;
import com.sengami.gui_diary.view.list.element.DiaryEntryListElementType;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class DiaryEntryListAdapter extends BaseAdapter<DiaryEntryListElement, DiaryEntryListElementType> {

    public DiaryEntryListAdapter(@NotNull final Context context) {
        super(
            context,
            DiaryEntryListElementType.values(),
            Arrays.asList(
                new DiaryEntryListDiaryEntryElementBinder(),
                new DiaryEntryListDateHeaderElementBinder()
            )
        );
    }
}