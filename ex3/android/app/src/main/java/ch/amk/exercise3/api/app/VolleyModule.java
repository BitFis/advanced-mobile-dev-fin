package ch.amk.exercise3.api.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import ch.amk.exercise3.api.CoreApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class VolleyModule {
    @Provides
    RequestQueue provideRequestQueue(Application app) {
        return Volley.newRequestQueue(app);
    }
}