package com.sengami.gui_diary.navigation;

public enum RequestCode {
    COMPOSE_DIARY_ENTRY(9000);

    private int code;

    RequestCode(final int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
