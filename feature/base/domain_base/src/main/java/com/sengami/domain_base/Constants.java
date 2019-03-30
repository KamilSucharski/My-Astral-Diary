package com.sengami.domain_base;

public final class Constants {
    public static final String DATABASE_NAME = "my_astral_diary.db";
    public static final String DATABASE_BACKUP_NAME = "my_astral_diary_%1$s.db";
    public static final String DATABASE_BACKUP_MIME_TYPE = "*/*";
    public static final String TEXT_EXPORT_NAME = "my_astral_diary_%1$s.txt";
    public static final String TEXT_EXPORT_MIME_TYPE = "text/plain";

    public static final String TEXT_EXPORT_DATE_FORMAT = "==========\n%1$s\n\n";
    public static final String TEXT_EXPORT_ENTRY_FORMAT = "# %1$s\n%2$s\n\n";
    public static final String DISPLAYED_DATE_FORMAT = "dd MMMM yyyy";
    public static final String FILE_DATE_FORMAT = "yyyyMMdd_HHmm";

    public static final int MINIMAL_SPLASH_SCREEN_DURATION_MILLISECONDS = 1000;
}