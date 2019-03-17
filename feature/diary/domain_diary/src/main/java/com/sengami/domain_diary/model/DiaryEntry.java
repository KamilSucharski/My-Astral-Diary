package com.sengami.domain_diary.model;

import com.sengami.domain_base.model.BaseModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public class DiaryEntry extends BaseModel {

    @Nullable
    private Integer id = null;
    private Date date = new Date();
    private String title = "";
    private String body = "";

    @Override
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