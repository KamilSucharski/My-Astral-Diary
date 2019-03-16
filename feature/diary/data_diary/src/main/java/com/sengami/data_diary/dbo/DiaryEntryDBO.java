package com.sengami.data_diary.dbo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sengami.domain_diary.model.DiaryEntryStatus;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@DatabaseTable
public class DiaryEntryDBO {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private Date date;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField(canBeNull = false)
    private String body;

    @DatabaseField(unknownEnumName = "UNKNOWN")
    private DiaryEntryStatus status;

    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(@NotNull final Integer id) {
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

    @NotNull
    public DiaryEntryStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull final DiaryEntryStatus status) {
        this.status = status;
    }
}