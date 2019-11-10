package ch.amk.exercise1;

import android.util.Log;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class AndroidLogMock {

    @BeforeClass
    public static void setup() {
        PowerMockito.mockStatic(Log.class);

        // Log warnings to the console
        when(Log.w(anyString(), anyString())).thenAnswer((InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            if (args.length > 1) { //cause I'm paranoid
                System.out.println("Tag:" + args[0] + " Msg: " + args[1]);
            }
            return null;
        });
        Log.w("My Tag", "This is a warning");
    }
}