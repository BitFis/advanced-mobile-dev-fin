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

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public abstract class AndroidLogMock {

    private static HashMap<Integer, String> LOG_LEVELS = new HashMap<Integer, String>(){{
        put(Log.VERBOSE, "VERBOSE");
        put(Log.DEBUG, "DEBUG");
        put(Log.INFO, "INFO");
        put(Log.WARN, "WARN");
        put(Log.ERROR, "ERROR");
        put(Log.ASSERT, "ASSERT");
    }};

    private static Answer<?>  getLogInvocation(int logLevel) {
        return (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();

            if(args.length > 1 && args.length < 3) {
                AndroidLogMock.log(logLevel, args[0].toString(), args[1].toString());
            } else if (args.length > 1 && args[2] instanceof Throwable) { //cause I'm paranoid
                AndroidLogMock.log(logLevel, args[0].toString(), args[1].toString(), (Throwable) args[2]);
            } else {
                new Exception("no matching function found with correct number and/or type of arguments");
            }

            return null;
        };
    }

    private static void log(int logLevel, String tag, String message) {
        System.out.println("[" + LOG_LEVELS.get(logLevel) + "}" + " Tag:" + tag + " Msg: " + message);
    }

    private static void log(int logLevel, String tag, String message, Throwable throwable) {
        AndroidLogMock.log(logLevel, tag, message);

        System.out.println("Exception: ");
        throwable.printStackTrace();
    }

    @BeforeClass
    public static void setupLogMocks() {
        PowerMockito.mockStatic(Log.class);

        when(Log.v(anyString(), anyString())).thenAnswer(AndroidLogMock.getLogInvocation(Log.VERBOSE));
        when(Log.v(anyString(), anyString(), any(Throwable.class))).thenAnswer(AndroidLogMock.getLogInvocation(Log.VERBOSE));

        when(Log.d(anyString(), anyString())).thenAnswer(AndroidLogMock.getLogInvocation(Log.DEBUG));
        when(Log.d(anyString(), anyString(), any(Throwable.class))).thenAnswer(AndroidLogMock.getLogInvocation(Log.DEBUG));

        when(Log.i(anyString(), anyString())).thenAnswer(AndroidLogMock.getLogInvocation(Log.INFO));
        when(Log.i(anyString(), anyString(), any(Throwable.class))).thenAnswer(AndroidLogMock.getLogInvocation(Log.INFO));

        when(Log.w(anyString(), anyString())).thenAnswer(AndroidLogMock.getLogInvocation(Log.WARN));
        when(Log.w(anyString(), anyString(), any(Throwable.class))).thenAnswer(AndroidLogMock.getLogInvocation(Log.WARN));

        when(Log.e(anyString(), anyString())).thenAnswer(AndroidLogMock.getLogInvocation(Log.ERROR));
        when(Log.e(anyString(), anyString(), any(Throwable.class))).thenAnswer(AndroidLogMock.getLogInvocation(Log.ERROR));

        when(Log.wtf(anyString(), anyString())).thenAnswer(AndroidLogMock.getLogInvocation(Log.ASSERT));
        when(Log.wtf(anyString(), anyString(), any(Throwable.class))).thenAnswer(AndroidLogMock.getLogInvocation(Log.ASSERT));
    }
}