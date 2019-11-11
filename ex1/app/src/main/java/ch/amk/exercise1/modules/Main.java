package ch.amk.exercise1.modules;

import android.app.Activity;
import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import ch.amk.exercise1.App;
import ch.amk.exercise1.PostActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class Main {

    @Provides
    RequestQueue provideRequestQueue(Application app) {
        return Volley.newRequestQueue(app);
    }

}
