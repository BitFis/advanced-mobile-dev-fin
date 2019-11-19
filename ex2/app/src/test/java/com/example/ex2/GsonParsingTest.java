package com.example.ex2;

import com.example.ex2.models.openweather.OpenWeatherResult;
import com.example.ex2.modules.GsonModule;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import xjava.nio.charset.Charsets;

import static org.junit.Assert.assertEquals;

public class GsonParsingTest {

    @Test
    public void testParsingOpenWeatherResult() throws IOException {
        String input = Resources.toString(Resources.getResource("sydney_current_weather.json"), Charsets.UTF_8);

        Gson gson = new GsonModule().provideGson();

        OpenWeatherResult result = gson.fromJson(input, OpenWeatherResult.class);

        assertEquals("City of Sydney", result.getName());
    }

}

