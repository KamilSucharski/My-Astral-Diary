package com.sengami.domain_splash.model;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class DiaryEntry {

    private int id;
    private Date date;
    private String title;
    private String body;
    private DiaryEntryStatus status;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
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