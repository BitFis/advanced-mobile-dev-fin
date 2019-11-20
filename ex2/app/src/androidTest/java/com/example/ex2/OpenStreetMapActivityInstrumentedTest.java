package com.example.ex2;

import android.content.Intent;
import android.net.Network;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.volley.RequestQueue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
public class OpenStreetMapActivityInstrumentedTest extends ActivityInstrumentationTest<OpenStreetMapActivity>
        implements ActivityInstrumentationTest.InjectableInstrumentationTest {

    @Mock
    Network network;

    @Override
    public Class getActivityClass() {
        return OpenStreetMapActivity.class;
    }

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void testOpenStreetMapLocation() {
        this.activityTestRule.launchActivity(new Intent());
    }
}
