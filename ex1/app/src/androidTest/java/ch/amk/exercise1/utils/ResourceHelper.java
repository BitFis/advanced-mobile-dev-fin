package ch.amk.exercise1.utils;

import android.content.Context;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;

import androidx.test.platform.app.InstrumentationRegistry;

public final class ResourceHelper {
    public final static String loadAsset(String asset) throws IOException {
        Context ctx = InstrumentationRegistry.getInstrumentation().getContext();
        return CharStreams.toString(new InputStreamReader(ctx.getResources().getAssets().open(asset)));
    }
}
