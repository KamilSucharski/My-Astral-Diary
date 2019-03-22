package com.sengami.data_base.dbo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@DatabaseTable(tableName = "diary_entry")
public final class DiaryEntryDBO extends BaseDBO {

    @DatabaseField(columnName = "id", generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "date", canBeNull = false)
    private Date date;

    @DatabaseField(columnName = "title", canBeNull = false)
    private String title;

    @DatabaseField(columnName = "body", canBeNull = false)
    private String body;

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable final Integer id) {
        this.id = id;
    }

    @NotNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NotNull final Date date) {
        this.date = date;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull final String title) {
        this.title = title;
    }

    @NotNull
    public String getBody() {
        return body;
    }

    public void setBody(@NotNull final String body) {
        this.body = body;
    }
}