package com.sengami.data_settings.operation.local;

import com.sengami.data_base.util.DatabaseFileProvider;
import com.sengami.domain_base.Constants;
import com.sengami.domain_base.operation.BaseOperation;
import com.sengami.domain_base.operation.configuration.OperationConfiguration;
import com.sengami.domain_settings.operation.CreateBackupOperation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import io.reactivex.Observable;

public final class CreateBackupOperationLocal extends BaseOperation<Boolean> implements CreateBackupOperation {

    @NotNull
    private final DatabaseFileProvider databaseFileProvider;
    @Nullable
    private OutputStream backupFileOutputStream;

    public CreateBackupOperationLocal(@NotNull final OperationConfiguration operationConfiguration,
                                      @NotNull final DatabaseFileProvider databaseFileProvider) {
        super(operationConfiguration);
        this.databaseFileProvider = databaseFileProvider;
    }

    @Override
    @NotNull
    public CreateBackupOperation withBackupFileOutputStream(@NotNull final OutputStream backupFileOutputStream) {
        this.backupFileOutputStream = backupFileOutputStream;
        return this;
    }

    @Override
    protected Observable<Boolean> getObservable() {
        return Observable.fromCallable(() -> {
            if (backupFileOutputStream == null) {
                throw new IllegalArgumentException("[OutputStream backupFileOutputStream] has not been set in CreateBackupOperationLocal");
            }

            final File database = databaseFileProvider.provide(Constants.DATABASE_NAME);
            final FileInputStream inputStream = new FileInputStream(database);
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                backupFileOutputStream.write(buffer, 0, length);
            }
            backupFileOutputStream.flush();
            backupFileOutputStream.close();
            inputStream.close();
            return true;
        });
    }
}