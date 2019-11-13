package ch.amk.exercise1.modules;

import android.app.Application;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.Volley;

import org.mockito.Mockito;

import javax.inject.Singleton;

import ch.amk.exercise1.ImmediateResponseDelivery;
import ch.amk.exercise1.service.OpenWeatherManager;
import ch.amk.exercise1.utils.RequestQueueMockBuilder;
import dagger.Module;
import dagger.Provides;

@Module
public class MainMock {

    @Singleton
    @Provides
    static Network provideNetwork() {
        return Mockito.mock(Network.class);
    }

    @Singleton
    @Provides
    static RequestQueue provideRequestQueue(Network network) {
        return new RequestQueueMockBuilder().network(network).create();
    }

    @Singleton
    @Provides
    static OpenWeatherManager provideOpenWeatherManager() {
        return Mockito.mock(OpenWeatherManager.class);
    }
}
