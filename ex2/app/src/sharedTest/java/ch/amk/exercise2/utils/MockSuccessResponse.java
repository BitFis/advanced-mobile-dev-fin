package ch.amk.exercise2.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MockSuccessResponse extends NetworkResponse {
    public MockSuccessResponse(@NonNull String message) {
        super(message.getBytes());
    }

    public MockSuccessResponse(@NonNull URL resource) throws IOException {
        super(Resources.toByteArray(resource));
    }

    public MockSuccessResponse(@NonNull Class<?> contextClass, @NonNull String resource) throws IOException {
        this(Resources.getResource(contextClass, resource));
    }

    public MockSuccessResponse(@NonNull Context context, @NonNull String assetFileName) throws IOException {
        this(context.getResources().getAssets().open(assetFileName));
    }

    public MockSuccessResponse(@NonNull InputStream inputStream) throws IOException {
        super(ByteStreams.toByteArray(inputStream));
    }

    public static NetworkResponse fromResource(String file) throws IOException {
        return new MockSuccessResponse(Resources.getResource(file));
    }

    public static NetworkResponse fromAssets(Context ctx, String file) throws IOException {
        return new MockSuccessResponse(ctx, file);
    }

}
