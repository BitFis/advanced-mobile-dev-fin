package ch.amk.exercise3.api.ui;

import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ch.amk.exercise3.api.MainActivity;
import ch.amk.exercise3.api.R;
import ch.amk.exercise3.api.di.MockApplication;
import ch.amk.exercise3.api.models.Embedded;
import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.models.Feedbacks;
import ch.amk.exercise3.api.service.FeedbackService;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.AllOf.allOf;
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

    @Before
    public void setup() {
        initMocks(this);

        MockApplication app = ((MockApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());
        app.getComponent().inject(this);
    }

    @Test
    public void testChangeItemInList() throws InterruptedException {

        Feedbacks feedbacks = new Feedbacks().withEmbedded(new Embedded().withFeedback(new ArrayList<Feedback>() {{
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

        when(this.feedbackService.getAll()).thenReturn(feedbacks);

        this.activityRule.launchActivity(new Intent());

        onView(allOf(withId(R.id.material_drawer_name), withText(containsString("Hans Zimmer")))).perform(click());
    }
}
