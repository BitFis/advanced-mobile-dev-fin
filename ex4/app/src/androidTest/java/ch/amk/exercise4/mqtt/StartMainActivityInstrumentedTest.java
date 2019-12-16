package ch.amk.exercise4.mqtt;

import android.Manifest;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StartMainActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class); // <1>

    @Test
    public void testStartingActivity() {
        rule.getScenario().onActivity(activity -> { // <2>
            // your asserts
        }); // <3>
    }

}

/** tag::description[]
<1> Create a scenario, for every test call it will create a new Scenario, which
    will start the Activity.
<2> This is not needed, but to be able to access the activity class, validation and calls to
    the activity needs to be done here.
<3> Set an debug point here, this will allow keeping the activity open.
    end::description[] */
