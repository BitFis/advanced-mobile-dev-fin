package com.example.ex2.ui;

import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.ex2.CoreApplication;
import com.google.android.gms.maps.model.Marker;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class WeatherPopupAdapterInstrumenteatedTest {

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(
            TestActivity.class,
            false,
            true
    );

    private Context ctx;
    private CoreApplication app;

    @Before
    public void setup() {
        this.ctx = InstrumentationRegistry.getInstrumentation().getContext();
        this.app = (CoreApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

    }

    @Test
    public void testShowWeatherDetailInformation() {


        Intent intent = new Intent();
        this.activityTestRule.launchActivity(intent);

        Marker marker = mock(Marker.class);

        //when(marker.getPosition()).thenReturn(new LatLng())
        //when(marker.getPosition()).thenReturn(new LatLng())

        WeatherPopupAdapter weatherPopupAdapter = new WeatherPopupAdapter(this.activityTestRule.getActivity());
        weatherPopupAdapter.getInfoContents(marker);
    }

}
