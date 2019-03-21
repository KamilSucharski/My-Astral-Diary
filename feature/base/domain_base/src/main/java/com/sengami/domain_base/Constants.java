package com.sengami.domain_base;

public class Constants {
    public static final String DATABASE_ALIAS = "my_astral_diary";
    public static final String DATABASE_EXTENSION = "db";
    public static final String TEXT_FILE_EXTENSION = "db";
    public static final String DATABASE_NAME = DATABASE_ALIAS + "." + DATABASE_EXTENSION;
    public static final String DATABASE_PATH = "databases/" + DATABASE_NAME;
    public static final String DATABASE_BACKUP_NAME_FORMAT = DATABASE_ALIAS + "_%1$s." + DATABASE_EXTENSION;
    public static final String TEXT_EXPORT_NAME_FORMAT = DATABASE_ALIAS + "_%1$s." + TEXT_FILE_EXTENSION;

    public static final String DISPLAYED_DATE_FORMAT = "dd MMMM yyyy";
    public static final String FILE_DATE_FORMAT = "yyyyMMdd_HHmm";

    public static final int MINIMAL_SPLASH_SCREEN_DURATION_MILLISECONDS = 1000;
}