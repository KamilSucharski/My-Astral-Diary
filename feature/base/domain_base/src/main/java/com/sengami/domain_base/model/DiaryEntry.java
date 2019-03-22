package com.sengami.domain_base.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.LocalDate;

public final class DiaryEntry extends BaseModel {

    @Nullable
    private Integer id = null;
    private LocalDate date = LocalDate.now();
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
    public LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull final LocalDate date) {
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