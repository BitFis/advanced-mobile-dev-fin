package ch.amk.exercise1;

import com.android.volley.ExecutorDelivery;

import org.junit.Rule;

import java.util.concurrent.Executor;

import androidx.test.rule.ActivityTestRule;

/**
 * A ResponseDelivery for testing that immediately delivers responses instead of posting back to the
 * main thread.
 */
public class ImmediateResponseDelivery extends ExecutorDelivery {

    public ImmediateResponseDelivery() {
        super((command) -> command.run());
    }
}
