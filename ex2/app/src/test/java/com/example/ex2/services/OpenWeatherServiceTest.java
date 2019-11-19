package com.example.ex2.services;

import android.util.Log;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ex2.models.openweather.OpenWeatherResult;
import com.example.ex2.modules.GsonModule;
import com.example.ex2.modules.TimeModule;
import com.example.ex2.service.OpenWeatherService;
import com.example.ex2.service.OpenWeatherUrlSchema;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.GsonBuilder;

import org.awaitility.Awaitility;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
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

    @Mock
    Network mockNetwork;

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
        TimeModule timeModule = new TimeModule();

        when(mockNetwork.performRequest(any())).thenReturn(MockSuccessResponse.fromResource("sydney_current_weather.json"));

        this.service.getLatLng(
                new LatLng(12, 12),
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

        Instant sunrise = Instant.ofEpochSecond(this.result.getSys().getSunrise());
        Instant sunset = Instant.ofEpochSecond(this.result.getSys().getSunset());

        ZoneId zone = ZoneId.ofOffset("UTC", ZoneOffset.ofTotalSeconds(this.result.getTimezone()));

        //(temperature, humidity, wind speed, sun rise and sun set
        assertEquals("City of Sydney", this.result.getName());
        assertEquals("UTC+11:00", zone.toString());
        assertEquals("05:41", timeModule.provideTime().withZone(zone).format(sunrise));
        assertEquals("19:40", timeModule.provideTime().withZone(zone).format(sunset));
        assertEquals((Double)19.07, this.result.getMain().getTemp());
        assertEquals((Integer) 82, this.result.getMain().getHumidity());
        assertEquals((Double)8.7, this.result.getWind().getSpeed());
    }

}
