package com.sengami.gui_base.navigation;

public enum RequestCode {
    SAVE_BACKUP_FILE(9001),
    LOAD_BACKUP_FILE(9002),
    SAVE_TEXT_FILE(9003),
    COMPOSE_DIARY_ENTRY(9004);

    private final int code;

    RequestCode(final int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}