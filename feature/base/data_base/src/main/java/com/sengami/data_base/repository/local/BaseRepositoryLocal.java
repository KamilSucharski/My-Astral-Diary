package com.sengami.data_base.repository.local;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sengami.domain_base.model.BaseModel;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public abstract class BaseRepositoryLocal<T extends BaseModel> {

    protected Dao<T, Integer> dao;

    public BaseRepositoryLocal(@NotNull final ConnectionSource connectionSource,
                               @NotNull final Class<T> clazz) {
        initializeDAO(connectionSource, clazz);
    }

    private void initializeDAO(@NotNull final ConnectionSource connectionSource,
                               @NotNull final Class<T> clazz) {
        try {
            this.dao = DaoManager.createDao(connectionSource, clazz);
        } catch (final SQLException exception) {
            throw new IllegalArgumentException("DAO couldn't be initialized", exception);
        }
    }
}