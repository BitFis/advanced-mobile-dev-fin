package com.example.ex2;

import android.app.Activity;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.ex2.CoreApplication;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Before;
import org.junit.Rule;

public abstract class ActivityInstrumentationTest<T extends Activity> {
    protected Context ctx;
    protected CoreApplication app;
    protected CoreComponentMock component;

    @Rule
    public ActivityTestRule<T> activityTestRule = new ActivityTestRule<>(
            this.getActivityClass(),
            false,
            true
    );

    public abstract Class getActivityClass();

    @Before
    public void setupInstrumentedTestsVariables() {
        this.ctx = InstrumentationRegistry.getInstrumentation().getContext();
        this.app = (CoreApplication)InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        // inject mocks
        this.component = DaggerCoreComponentMock
                .builder()
                .application(this.app)
                .build();
        this.component.inject(this.app);
        this.app.setComponent(this.component);
    }

    public interface InjectableInstrumentationTest { }
}
