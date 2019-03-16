package com.sengami.data_splash.dbo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sengami.domain_splash.model.DiaryEntryStatus;

import java.util.Date;

@DatabaseTable
public class DiaryEntryDBO {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private Date date;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField(canBeNull = false)
    private String body;

    @DatabaseField(unknownEnumName = "UNKNOWN")
    private DiaryEntryStatus status;
}