package ch.amk.exercise1.spinner;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import ch.amk.exercise1.App;
import ch.amk.exercise1.DaggerMyComponentMock;
import ch.amk.exercise1.MyComponentMock;
import ch.amk.exercise1.PostActivity;
import ch.amk.exercise1.R;
import ch.amk.exercise1.models.openweather.OpenWeather;
import ch.amk.exercise1.utils.MockSuccessResponse;

import static org.hamcrest.CoreMatchers.*;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class CitySelectorInstrumentedTest {

    @Rule
    public ActivityTestRule<CitySelectorActivity> activityRule = new ActivityTestRule<>(
            CitySelectorActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent

    private Context ctx;
    private App app;

    @Inject
    Network mockNetwork;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.ctx = InstrumentationRegistry.getInstrumentation().getContext();
        this.app = (App)InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        MyComponentMock component = DaggerMyComponentMock
                .builder()
                .application(this.app)
                .build();
        component.inject(this.app);
        component.inject(this);

        this.app.setComponent(component);
    }

    @Test
    public void testShowCityDetails() throws VolleyError, IOException {
        Intent intent = new Intent();
        this.activityRule.launchActivity(intent);

        Mockito
                .when(mockNetwork.performRequest(ArgumentMatchers.any()))
                .thenAnswer(invocation -> new MockSuccessResponse(this.ctx, "openweather_city_rovaniemi.json"));

        // select spinner element
        int spinnerId = R.id.city_spinner;
        String cityToSelect = "Rovaniemi, fi";

        onView(withId(spinnerId)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(cityToSelect))).perform(click());
        onView(withId(spinnerId)).check(ViewAssertions.matches(withSpinnerText(containsString(cityToSelect))));

        Awaitility.await().until(() -> {
            AtomicBoolean value = new AtomicBoolean();
            try {
                this.activityRule.getActivity().runOnUiThread(() ->
                    value.set(this.activityRule.getActivity().findViewById(R.id.data_view).getVisibility() == View.VISIBLE)
                );
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return value.get();
        });

        onView(withId(R.id.action_open_detail_weather)).perform(click());
    }

    @Test
    public void testCitySelectorSpinner() {
        Intent intent = new Intent();
        this.activityRule.launchActivity(intent);

        // todo add selection tests
    }
}
