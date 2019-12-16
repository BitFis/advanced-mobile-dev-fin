package ch.amk.exercise3.api.ui;

import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

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
import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
                    .withId(101)
                    .withName("Hans Zimmer")
                    .withValue("This is a comment")
                    .withLocation("Rovaniemi, fi"));
            add(new Feedback()
                    .withId(202)
                    .withName("Fritz Matz")
                    .withLocation("Zug, ch")
                    .withValue("Swiss Comment"));
        }}));

        when(this.feedbackService.getAll()).thenReturn(CompletableFuture.completedFuture(feedbacks));

        this.activityRule.launchActivity(new Intent());
    }

    @Test
    public void testChangeItemInList() throws UiObjectNotFoundException, InterruptedException {
        onView(allOf(withId(R.id.material_drawer_name), withText(containsString("Hans Zimmer")))).perform(click());

        when(this.feedbackService.save(any())).thenAnswer(invocation ->
                CompletableFuture.completedFuture(invocation.getArgument(0))
        );

        device.findObject(new UiSelector().textContains("Hans Zimmer")).setText("New Newman");
        device.findObject(new UiSelector().textContains("comment")).setText("Changed Comment");
        device.findObject(new UiSelector().textContains("fi")).setText("Zug, ch");

        onView(withId(R.id.button_save)).perform(click());

        verify(this.feedbackService).save(any());

        // give the activity time to update, max 5 seconds
        device.waitForWindowUpdate(null, 5000);

        // check if new item can be found and old one disapeared
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

        Feedback newFeedback = new Feedback()
                .withName("Other Man")
                .withLocation("Paris, fr")
                .withValue("Commenting");

        when(this.feedbackService.save(any())).thenReturn(CompletableFuture.completedFuture(newFeedback));

        device.findObject(new UiSelector().description("Name")).setText(newFeedback.getName());
        device.findObject(new UiSelector().description("Feedback")).setText(newFeedback.getValue());
        device.findObject(new UiSelector().description("Location")).setText(newFeedback.getLocation());

        onView(withId(R.id.button_save)).perform(click());

        onView(allOf(withId(R.id.material_drawer_name), withText(containsString(newFeedback.getName()))))
                .check(matches(isDisplayed()));

        ArgumentCaptor<Feedback> feedbackArgumentCaptor = ArgumentCaptor.forClass(Feedback.class);
        verify(this.feedbackService).save(feedbackArgumentCaptor.capture());

        assertEquals(newFeedback, feedbackArgumentCaptor.getValue());
    }

    @Test
    public void testDeleteFeedback() throws UiObjectNotFoundException, InterruptedException {
        device.findObject(new UiSelector().textContains("Fritz Matz")).swipeRight(10);

        Thread.sleep(200);

        verify(this.feedbackService).delete(202);
    }
}
