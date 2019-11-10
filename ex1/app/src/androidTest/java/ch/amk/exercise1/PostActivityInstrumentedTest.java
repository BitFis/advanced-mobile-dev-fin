package ch.amk.exercise1;


import android.content.Intent;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;

@RunWith(AndroidJUnit4.class)
public class PostActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<PostActivity> activityRule
            = new ActivityTestRule<>(
            PostActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent

    @Test
    public void fetchPost() {
        Intent intent = new Intent();
        this.activityRule.launchActivity(intent);

        AtomicBoolean requestDone = new AtomicBoolean(false);

        PostActivity activity = this.activityRule.getActivity();

        activity.getRequestQueue().addRequestFinishedListener(request -> {
            requestDone.set(true);
            Log.i("test", "request received");
        });

        activity.fetchPost(activity.ENDPOINT);




        // todo test
    }
}
