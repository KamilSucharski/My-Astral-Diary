package pl.apzumi.internal_storage.di.module;

import android.content.Context;

import com.sengami.data_base.util.InternalStoragePathProvider;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;
import pl.apzumi.internal_storage.AndroidInternalStoragePathProvider;

@Module
public final class InternalStoragePathProviderModule {

    @Provides
    @NotNull
    InternalStoragePathProvider internalStoragePathProvider(@NotNull final Context context) {
        return new AndroidInternalStoragePathProvider(context);
    }
}