package com.example.ex2.modules;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import dagger.Module;
import dagger.Provides;

@Module
public class VolleyModule {

    @Provides
    RequestQueue provideRequestQueue(Application app) {
        return Volley.newRequestQueue(app);
    }

}
