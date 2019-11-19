package com.example.ex2;

import android.content.UriMatcher;
import android.net.Uri;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ex2.service.OpenWeatherUrlSchema;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class OpenWeatherUrlSchemaTest {

    private OpenWeatherUrlSchema openWeatherUrlSchema;

    @Before
    public void setup() {
        this.openWeatherUrlSchema = new OpenWeatherUrlSchema();
    }

    @Test
    public void testChangeAppid() {
        String appid = "abcd";

        this.openWeatherUrlSchema.appid(appid);

        Uri parsed = Uri.parse(this.openWeatherUrlSchema.toString());

        assertEquals(appid, parsed.getQueryParameter("appid"));
    }

    @Test
    public void testLatLngSchemaCreation() {
        LatLng latLng = new LatLng(-33.87365, 151.20689);

        this.openWeatherUrlSchema.latLng(latLng);
        this.openWeatherUrlSchema.appid("01f4ce4de22786346799bfec88c18db8");

        Uri parsed = Uri.parse(this.openWeatherUrlSchema.toString());

        assertEquals(latLng.latitude, parsed.getQueryParameter("lat"));
        assertEquals(latLng.longitude, parsed.getQueryParameter("lon"));
    }
}
