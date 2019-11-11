package ch.amk.exercise1.modules;

import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public class Gson {

    @Provides
    com.google.gson.Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        return gsonBuilder.create();
    }

}
