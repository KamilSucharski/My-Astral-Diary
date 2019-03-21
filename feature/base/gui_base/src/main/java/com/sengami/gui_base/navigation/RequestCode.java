package com.sengami.gui_base.navigation;

public enum RequestCode {
    COMPOSE_DIARY_ENTRY(9000),
    PICK_BACKUP_FILE(9001);

    private int code;

    RequestCode(final int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
