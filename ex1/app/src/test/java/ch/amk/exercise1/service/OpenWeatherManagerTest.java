package ch.amk.exercise1.service;

import android.util.Log;

import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicBoolean;

import ch.amk.exercise1.AndroidLogMock;
import ch.amk.exercise1.App;
import ch.amk.exercise1.DaggerMyComponent;
import ch.amk.exercise1.MyComponent;
import ch.amk.exercise1.models.openweather.OpenWeather;
import ch.amk.exercise1.utils.MockSuccessResponse;
import ch.amk.exercise1.utils.RequestQueueMockBuilder;

public class OpenWeatherManagerTest extends AndroidLogMock {

    private OpenWeatherManager manager;

    private Gson gson;
    private RequestQueue requestQueue;
    private AtomicBoolean requestDone = new AtomicBoolean(false);

    @Mock Network mockNetwork;

    @Captor ArgumentCaptor<Request> requestArgumentCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setupGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();
    }

    @Before
    public void setupRequestQueue() {
        this.requestQueue = new RequestQueueMockBuilder()
                .network(mockNetwork)
                .create();
    }

    @Test
    public void testUrlString() throws VolleyError, IOException {
        this.manager = new OpenWeatherManager(this.gson, this.requestQueue);


        NetworkResponse response = new MockSuccessResponse(Resources.getResource("openweather_default_city.json"));
        Mockito
                .when(mockNetwork.performRequest(requestArgumentCaptor.capture()))
                .thenReturn(response);

        this.manager.get("Zug,ch", resp -> this.requestDone.set(true), error -> this.requestDone.set(true));

        Awaitility.await().untilTrue(this.requestDone);

        Mockito.verify(mockNetwork, Mockito.atLeastOnce()).performRequest(ArgumentMatchers.any());

        Assert.assertThat(
                this.requestArgumentCaptor.getValue().getUrl(),
                Matchers.matchesPattern("^https:\\/\\/api\\.openweathermap\\.org\\/data\\/2\\.5\\/weather\\?q\\=Zug, *ch\\&units=metric&appid\\=[0-9a-zA-Z]+$"));

    }

    @Test
    public void testLoadCityInformation() throws VolleyError, IOException {
        this.manager = new OpenWeatherManager(this.gson, this.requestQueue);

        NetworkResponse response = new MockSuccessResponse(Resources.getResource("openweather_default_city.json"));
        Mockito
                .when(mockNetwork.performRequest(ArgumentMatchers.any()))
                .thenReturn(response);

        this.manager.get(
                "Zug, ch",
                resp -> {
                    Assert.assertEquals("Zug", resp.getName());
                    Assert.assertEquals("CH", resp.getSys().getCountry());
                    Assert.assertEquals((Double) 3.6, resp.getWind().getSpeed());
                    Assert.assertEquals((Integer) 82, resp.getMain().getHumidity());
                    Assert.assertEquals((Double) 6.03, resp.getMain().getTemp());

                    this.requestDone.set(true);
                },
                error -> this.requestDone.set(true)
        );

        Awaitility.await().untilTrue(this.requestDone);

        //todo actual test
    }

}
