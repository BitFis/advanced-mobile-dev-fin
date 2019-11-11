package ch.amk.exercise1;

import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

@RunWith(AndroidJUnit4.class)
public class ItemListActivityTest {

    private Context ctx;

    @Rule
    public ActivityTestRule<ScrollingActivity> activityRule = new ActivityTestRule<>(
            ScrollingActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.ctx = InstrumentationRegistry.getInstrumentation().getContext();
    }

    @Test
    public void testItemlist() throws InterruptedException {
        Intent intent = new Intent();
        this.activityRule.launchActivity(intent);
    }

}
