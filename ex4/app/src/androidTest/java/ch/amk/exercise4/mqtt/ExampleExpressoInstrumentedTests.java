package ch.amk.exercise4.mqtt;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExampleExpressoInstrumentedTests {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    private UiDevice device;

    @Before
    public void setupUiDevice() {
        this.device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testSelecting() {
        // tag::uiDevice[]
UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // end::uiDevice[]

        // tag::selectionExample[]
this.rule.getScenario().onActivity(activity -> {
    // select by description
    UiObject byDescription = device.findObject(new UiSelector().description("message_box"));

    // select by R.ID
    UiObject byRId = device.findObject(new UiSelector().description("message_box"));

    // select by content, also has textContains for some part of content, textMatches for regex, ...
    UiObject byContent = device.findObject(new UiSelector().text("Message"));

});
        // end::selectionExample[]
    }

    @Test
    public void testChangingTheText() {

    }

    @Test
    public void testAssertExamples() {

    }

}
