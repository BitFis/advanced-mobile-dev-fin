package ch.amk.exercise2;

import com.android.volley.ExecutorDelivery;

/**
 * A ResponseDelivery for testing that immediately delivers responses instead of posting back to the
 * main thread.
 */
public class ImmediateResponseDelivery extends ExecutorDelivery {
    public ImmediateResponseDelivery() {
        super((command) -> command.run());
    }
}
