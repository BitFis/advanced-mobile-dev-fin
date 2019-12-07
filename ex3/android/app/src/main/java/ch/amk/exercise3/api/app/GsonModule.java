package ch.amk.exercise3.api.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {
    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }
}
