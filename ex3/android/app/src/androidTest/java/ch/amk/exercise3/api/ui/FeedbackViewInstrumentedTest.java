package ch.amk.exercise3.api.ui;

import android.content.Intent;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.android.volley.toolbox.RequestFuture;

import java.util.ArrayList;

import javax.inject.Inject;

import ch.amk.exercise3.api.MainActivity;
import ch.amk.exercise3.api.R;
import ch.amk.exercise3.api.di.MockApplication;
import ch.amk.exercise3.api.models.Embedded;
import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.models.Feedbacks;
import ch.amk.exercise3.api.service.FeedbackService;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
public class FeedbackViewInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
        MainActivity.class,
        true,
        false
    );

    @Inject
    public FeedbackService feedbackService;

    private Feedbacks feedbacks;
    private UiDevice device;

    @Before
    public void setup() {
        initMocks(this);

        MockApplication app = ((MockApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());
        app.getComponent().inject(this);

        this.device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Before
    public void setupSampleData() {
        this.feedbacks = new Feedbacks().withEmbedded(new Embedded().withFeedback(new ArrayList<Feedback>() {{
            add(new Feedback()
                    .withId(1)
                    .withName("Hans Zimmer")
                    .withValue("This is a comment")
                    .withLocation("Rovaniemi, fi"));
            add(new Feedback()
                    .withId(2)
                    .withName("Fritz Matz")
                    .withLocation("Zug, ch")
                    .withValue("Swiss Comment"));
        }}));

        when(this.feedbackService.getAll()).thenAnswer(invocation -> {
            RequestFuture<Feedbacks> requestFuture = RequestFuture.newFuture();
            requestFuture.onResponse(feedbacks);
            return requestFuture;
        });

        this.activityRule.launchActivity(new Intent());
    }

    @Test
    public void testChangeItemInList() throws UiObjectNotFoundException, InterruptedException {
        onView(allOf(withId(R.id.material_drawer_name), withText(containsString("Hans Zimmer")))).perform(click());

        device.findObject(new UiSelector().textContains("Hans Zimmer")).setText("New Newman");
        device.findObject(new UiSelector().textContains("comment")).setText("Changed Comment");
        device.findObject(new UiSelector().textContains("fi")).setText("Zug, ch");

        onView(withId(R.id.button_save)).perform(click());

        ArgumentCaptor<Feedback> captor = ArgumentCaptor.forClass(Feedback.class);

        verify(this.feedbackService).save(captor.capture());

        Feedback values = captor.getValue();

        // check if new can be found
        onView(allOf(withId(R.id.material_drawer_name), withText(containsString("New Newman")))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.material_drawer_name), withText(containsString("Hans Zimmer")))).check(doesNotExist());
    }

    @Test
    public void testCancelUpdate() throws UiObjectNotFoundException {
        onView(allOf(withId(R.id.material_drawer_name), withText(containsString("Hans Zimmer")))).perform(click());

        device.findObject(new UiSelector().textContains("Hans Zimmer")).setText("New Newman");

        pressBack();

        onView(allOf(withId(R.id.material_drawer_name), withText(containsString("New Newman")))).check(doesNotExist());
    }

    @Test
    public void testAddingNewFeedback() throws UiObjectNotFoundException {
        onView(withId(R.id.button_add)).perform(click());

        device.findObject(new UiSelector().description("Name")).setText("Other Man");
        device.findObject(new UiSelector().description("Feedback")).setText("Commenting");
        device.findObject(new UiSelector().description("Location")).setText("Paris, fr");

        onView(withId(R.id.button_save)).perform(click());

        onView(allOf(withId(R.id.material_drawer_name), withText(containsString("Other Man"))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testDeleteFeedback() {
        
    }
}
