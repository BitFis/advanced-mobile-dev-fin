package com.example.ex2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PatternMatcher;

import org.apache.commons.lang3.RegExUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.ex2.service.OpenWeatherService;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.annotation.RegEx;
import javax.inject.Inject;

import ch.amk.exercise2.utils.MockSuccessResponse;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.matchesRegex;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MapsActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MapsActivity> activityRule = new ActivityTestRule<>(
            MapsActivity.class,
            true,     // initialTouchMode
            false
    );   // launchActivity. False to customize the intent

    private Context ctx;
    private CoreApplication app;

    @Inject
    OpenWeatherService mockOpenWeatherService;

    @Inject
    Network mockNetwork;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.ctx = InstrumentationRegistry.getInstrumentation().getContext();
        this.app = (CoreApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        CoreComponentMock component = DaggerCoreComponentMock
                .builder()
                .application(this.app)
                .build();
        component.inject(this.app);
        component.inject(this);
        this.app.setComponent(component);
    }

    /**
     * This test needs internet access, since gmaps
     * is only available online, not mocking is possible.
     */
    @Test
    public void testCustomWindowPopup() throws UiObjectNotFoundException, IOException, VolleyError {

        when(mockNetwork.performRequest(any()))
                .thenAnswer(invocation -> {
                    Request<String> request = invocation.getArgument(0);
                    if(Pattern.compile(".*/weather.*").matcher(request.getUrl()).matches()) {
                        return MockSuccessResponse.fromAssets(this.ctx, "sydney_current_weather.json");
                    }
                    return MockSuccessResponse.fromAssets(this.ctx, "sydney_forecast_weather.json");
                });

        Intent intent = new Intent();
        this.activityRule.launchActivity(intent);

        // clicking marker
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        UiObject marker = device.findObject(new UiSelector().descriptionContains("Sydney"));
        marker.click();
    }

}
