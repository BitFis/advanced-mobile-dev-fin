package com.example.ex2;

import com.android.volley.Response;
import com.example.ex2.models.openweather.List;
import com.example.ex2.models.openweather.OpenWeatherForcast;
import com.example.ex2.modules.GsonModule;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import xjava.nio.charset.Charsets;

import static org.junit.Assert.assertEquals;

public class OpenWeatherForcastTest {

    @Test
    public void testGetAvrageTempreturePerDay() throws IOException {
        String input = Resources.toString(Resources.getResource("sydney_forcast_weather.json"), Charsets.UTF_8);
        OpenWeatherForcast forcast = new GsonModule().provideGson().fromJson(input, OpenWeatherForcast.class);

        Map<String, List> grouped = forcast.groupByDayMerge(OpenWeatherForcast::mergeAvrages);

        assertEquals((Double)18.163333333333334, grouped.get("19.11.2019").getMain().getTemp());
    }
}
