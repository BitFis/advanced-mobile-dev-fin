package ch.amk.exercise1.spinner;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

import org.awaitility.Awaitility;
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

        this.activityRule.getActivity().loadCity("rovaniemi,fi");

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
    }

    @Test
    public void testCitySelectorSpinner() {
        Intent intent = new Intent();
        this.activityRule.launchActivity(intent);

        // todo add selection tests
    }
}
