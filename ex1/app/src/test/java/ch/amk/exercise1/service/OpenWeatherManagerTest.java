package ch.amk.exercise1.service;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ch.amk.exercise1.AndroidLogMock;
import ch.amk.exercise1.App;
import ch.amk.exercise1.DaggerMyComponent;
import ch.amk.exercise1.MyComponent;
import ch.amk.exercise1.utils.RequestQueueMockBuilder;

public class OpenWeatherManagerTest extends AndroidLogMock {

    private OpenWeatherManager manager;

    private Gson gson;
    private RequestQueue requestQueue;

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
                .network(Mockito.mock(Network.class))
                .create();
    }

    @Test
    public void testLoadCityInformation() {
        this.manager = new OpenWeatherManager(this.gson, this.requestQueue);

    }

}
