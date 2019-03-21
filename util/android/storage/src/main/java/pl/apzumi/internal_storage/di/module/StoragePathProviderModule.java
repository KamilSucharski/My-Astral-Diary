package pl.apzumi.internal_storage.di.module;

import android.content.Context;

import com.sengami.data_base.util.ExternalStoragePathProvider;
import com.sengami.data_base.util.InternalStoragePathProvider;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;
import pl.apzumi.internal_storage.AndroidExternalStoragePathProvider;
import pl.apzumi.internal_storage.AndroidInternalStoragePathProvider;

@Module
public final class StoragePathProviderModule {

    @Provides
    @NotNull
    InternalStoragePathProvider internalStoragePathProvider(@NotNull final Context context) {
        return new AndroidInternalStoragePathProvider(context);
    }

    @Provides
    @NotNull
    ExternalStoragePathProvider externalStoragePathProvider() {
        return new AndroidExternalStoragePathProvider();
    }
}