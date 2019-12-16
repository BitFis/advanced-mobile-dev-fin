package ch.amk.exercise4.mqtt;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiSelector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
// select by description
UiObject byDescription = device.findObject(new UiSelector().description("message_box"));

// select by R.ID
UiObject2 byRId = device.findObject(By.res(
        "ch.amk.exercise4.mqtt", // <1>
        "message_box" // <2>
));

// select by content, also has textContains for some part of content, textMatches for regex, ...
UiObject byContent = device.findObject(new UiSelector().text("Message"));

// tag::ignore[]
assertTrue(byDescription.exists());
assertNotNull(byRId);
assertTrue(byContent.exists());
// end::ignore[]
        // end::selectionExample[]

/** tag::selectionExampleDesc[]
<1> The package name of the activity
<2> The R.id, sadly `R.id.message_box` does not work.
 end::selectionExampleDesc[] */

    }

    @Test
    public void testCompareText() {
        // tag::assertExample[]
UiObject element = device.findObject(new UiSelector().description("message_box"));

// check if exists
assertTrue(element.exists());
        // end::assertExample[]
    }

    @Test
    public void testChangingTheText() {
// tag::changingText[]
this.rule.getScenario().onActivity(activity -> {
    // ViewActions do not work here
});

// change text with espresso
onView(withId(R.id.message_box))
    .perform(replaceText("Hello World"));

// change text with UiAnimator
device.findObject(By.res("ch.amk.exercise4.mqtt", "message_box"))
        .setText("Hello World");
// end::changingText[]
    }

    @Test
    public void testAssertExamples() {

    }

}
