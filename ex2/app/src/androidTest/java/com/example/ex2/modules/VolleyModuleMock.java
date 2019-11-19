package com.example.ex2.modules;

import com.android.volley.Network;
import com.android.volley.RequestQueue;

import javax.inject.Singleton;

import ch.amk.exercise2.utils.RequestQueueMockBuilder;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class VolleyModuleMock {
    @Singleton
    @Provides
    public static Network provideNetwork() {
        return mock(Network.class);
    }

    @Singleton
    @Provides
    public static RequestQueue provideRequestQueue(Network network) {
        return new RequestQueueMockBuilder().network(network).create();
    }
}
