package ch.amk.exercise1.spinner;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import ch.amk.exercise1.PostActivity;

@RunWith(AndroidJUnit4.class)
public class CitySelectorInstrumentedTest {

    @Rule
    public ActivityTestRule<CitySelectorActivity> activityRule = new ActivityTestRule<>(
            CitySelectorActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent

    private Context ctx;
 
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.ctx = InstrumentationRegistry.getInstrumentation().getContext();
    }

    @Test
    public void testCitySelectorSpinner() {
        Intent intent = new Intent();
        this.activityRule.launchActivity(intent);

    }
}
