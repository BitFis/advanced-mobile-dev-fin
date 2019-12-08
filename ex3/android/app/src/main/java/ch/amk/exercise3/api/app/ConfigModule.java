package ch.amk.exercise3.api.app;

import android.net.Uri;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfigModule {

    @Provides
    @Named("backend_url")
    public Uri provideBackendUrl() {
        return  Uri.parse("http://172.28.128.3:8080");
    }

}
