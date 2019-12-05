package ch.amk.exercise3.api;

import android.content.Context;
import android.content.Intent;

import static org.mockito.Mockito.when;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import ch.amk.exercise3.api.di.MockApplication;
import ch.amk.exercise3.api.service.FeedbackService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Inject
    FeedbackService service;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            false
    );

    @Before
    public void setup() {
        initMocks(this);

        MockApplication app = ((MockApplication)InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());
        app.getComponent().inject(this);
    }

    @Test
    public void useAppContext() {
        this.activityRule.launchActivity(new Intent());

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ch.amk.exercise3.api", appContext.getPackageName());
    }
}
