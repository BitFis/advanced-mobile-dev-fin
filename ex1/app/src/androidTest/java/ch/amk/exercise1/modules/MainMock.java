package ch.amk.exercise1.modules;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

//@Module
public class MainMock {

    @Singleton
    @Provides
    static RequestQueue provideRequestQueue(Application app) {
        return Mockito.mock(RequestQueue.class);
    }

}
