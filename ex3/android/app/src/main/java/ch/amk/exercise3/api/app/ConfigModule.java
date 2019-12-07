package ch.amk.exercise3.api.app;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfigModule {

    @Provides
    @Named("backend_url")
    public String provideBackendUrl() {
        return "http://172.28.128.3:8080";
    }

}
