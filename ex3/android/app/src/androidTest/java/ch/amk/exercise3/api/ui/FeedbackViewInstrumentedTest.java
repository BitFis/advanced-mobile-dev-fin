package ch.amk.exercise3.api.ui;

import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import ch.amk.exercise3.api.MainActivity;
import ch.amk.exercise3.api.di.MockApplication;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
public class FeedbackViewInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
        MainActivity.class,
        true,
        false
    );

    @Before
    public void setup() {
        initMocks(this);

        MockApplication app = ((MockApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());
        app.getComponent().inject(this);
    }

    @Test
    public void testShowItemsInList() {
        this.activityRule.launchActivity(new Intent());


    }
}
