package ch.amk.exercise1;


import android.content.Context;
import android.content.Intent;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import ch.amk.exercise1.utils.ExceptionBox;
import ch.amk.exercise1.utils.MockSuccessResponse;

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

@RunWith(AndroidJUnit4.class)
public class PostActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<PostActivity> activityRule = new ActivityTestRule<>(
            PostActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent

    private Context ctx;
    private App app;

    @Inject protected RequestQueue requestQueue;
    @Inject protected Network mockNetwork;

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
    public void fetchPost() throws VolleyError, InterruptedException, IOException {
        AtomicBoolean requestDone = new AtomicBoolean(false);

        Mockito
                .when(mockNetwork.performRequest(ArgumentMatchers.any()))
                .then(invocation -> new MockSuccessResponse(this.ctx, "fetchPostMock.json"));

        requestQueue.addRequestFinishedListener(r -> {
            requestDone.set(true);
        });

        Intent intent = new Intent();
        this.activityRule.launchActivity(intent);

        Awaitility.await().untilTrue(requestDone);

        // check if ExceptionBox is showing
        Espresso.onView(ViewMatchers.withText(ExceptionBox.TITLE)).check(ViewAssertions.doesNotExist());
    }
}
