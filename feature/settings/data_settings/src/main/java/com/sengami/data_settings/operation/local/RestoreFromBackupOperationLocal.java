package com.sengami.data_settings.operation.local;

import com.sengami.data_base.util.DatabaseFileProvider;
import com.sengami.domain_base.Constants;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.operation.configuration.OperationConfiguration;
import com.sengami.domain_settings.operation.RestoreFromBackupOperation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;

public final class RestoreFromBackupOperationLocal extends BaseOperation<Boolean> implements RestoreFromBackupOperation {

    @NotNull
    private final DatabaseFileProvider databaseFileProvider;
    @Nullable
    private InputStream backupFileInputStream;

    public RestoreFromBackupOperationLocal(@NotNull final OperationConfiguration operationConfiguration,
                                           @NotNull final DatabaseFileProvider databaseFileProvider) {
        super(operationConfiguration);
        this.databaseFileProvider = databaseFileProvider;
    }

    @Override
    @NotNull
    public RestoreFromBackupOperation withBackupFileInputStream(@NotNull final InputStream backupFileInputStream) {
        this.backupFileInputStream = backupFileInputStream;
        return this;
    }

    @Override
    protected Observable<Boolean> getObservable() {
        return Observable.fromCallable(() -> {
            if (backupFileInputStream == null) {
                throw new IllegalArgumentException("[InputStream backupFileInputStream] has not been set in RestoreFromBackupOperationLocal");
            }

            final File database = databaseFileProvider.provide(Constants.DATABASE_NAME);
            final OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(database));
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = backupFileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            backupFileInputStream.close();
            return true;
        });
    }
}