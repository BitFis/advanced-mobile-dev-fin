package com.example.ex2.services;

import android.util.Log;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ex2.models.openweather.OpenWeatherResult;
import com.example.ex2.modules.GsonModule;
import com.example.ex2.service.OpenWeatherService;
import com.example.ex2.service.OpenWeatherUrlSchema;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.GsonBuilder;

import org.awaitility.Awaitility;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import ch.amk.exercise2.utils.MockSuccessResponse;
import ch.amk.exercise2.utils.RequestQueueMockBuilder;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class OpenWeatherServiceTest {

    private OpenWeatherService service;
    private OpenWeatherResult result;

    @Mock Network mockNetwork;

    @Before
    public void setup() {
        initMocks(this);

        this.service = new OpenWeatherService(new RequestQueueMockBuilder()
                .network(mockNetwork)
                .create(),
                new GsonModule().provideGson());
    }

    @Test
    public void testGetOpenweatherByLatLng() throws IOException, VolleyError {
        AtomicBoolean done = new AtomicBoolean(false);

        when(mockNetwork.performRequest(any())).thenReturn(MockSuccessResponse.fromResource("sydney_current_weather.json"));

        this.service.getLatLng(
                new LatLng(12,12),
                response -> {
                    OpenWeatherServiceTest.this.result = response;
                    done.set(true);
                },
                error -> {
                    error.printStackTrace();
                    Log.e("OpenWeatherServiceTest", error.toString());
                    done.set(true);
                }
        );

        await().untilTrue(done);

        assertEquals("City of Sydney", this.result.getName());
    }

}
