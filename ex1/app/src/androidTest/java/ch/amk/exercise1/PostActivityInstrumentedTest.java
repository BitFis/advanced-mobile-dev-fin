package ch.amk.exercise1;


import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;
import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import ch.amk.exercise1.utils.ExceptionBox;

import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

@RunWith(AndroidJUnit4.class)
public class PostActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<PostActivity> activityRule = new ActivityTestRule<>(
            PostActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent

    @Mock private Network mockNetwork;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Application app = (Application)instrumentation.getTargetContext().getApplicationContext();
    }

    @Test
    public void testOnView() {
        Espresso.onView(ViewMatchers.withId(R.id.textView2)).check(ViewAssertions.matches(ViewMatchers.withText("Hello World")));
    }

    @Test @Ignore
    public void fetchPost() throws VolleyError, InterruptedException {
        Intent intent = new Intent();
        this.activityRule.launchActivity(intent);

        AtomicBoolean requestDone = new AtomicBoolean(false);

        PostActivity activity = this.activityRule.getActivity();

        RequestQueue requestQueue = new RequestQueue(new NoCache(), mockNetwork, 1, new ImmediateResponseDelivery());
        requestQueue.start();

        activity.setRequestQueue(requestQueue);

        Mockito.when(mockNetwork.performRequest(ArgumentMatchers.argThat(argument -> argument.getUrl() == "google.com" ))).then(invocation -> {
            return new NetworkResponse(
                    201,
                    "Hello World".getBytes(),
                    false,
                    200,
                    Collections.emptyList());
        });

        activity.getRequestQueue().addRequestFinishedListener(r -> {
            requestDone.set(true);
        });

        activity.fetchPost(activity.ENDPOINT);

        Awaitility.await().untilTrue(requestDone);

        // check if ExceptionBox is showing
        //Espresso.onView(ViewMatchers.withText(ExceptionBox.TITLE));
        //.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Thread.sleep(1000);
        // todo test
    }
}
