package ch.amk.exercise3.api.utils;

import android.net.Uri;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.awaitility.Awaitility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

import ch.amk.exercise3.api.app.ConfigModule;
import ch.amk.exercise3.api.app.GsonModule;
import ch.amk.exercise3.api.app.VolleyModule;
import ch.amk.exercise3.api.di.MockApplication;
import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.models.Feedbacks;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
public class RequestBuilderInstrumentedTest {

    private RequestQueue requestQueue;
    private Uri backendUrl;
    private Gson gson;

    private String payload;

    @Before
    public void setup() {
        initMocks(this);

        MockApplication app = ((MockApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());
        app.getComponent().inject(this);

        this.requestQueue = Volley.newRequestQueue(app);
        this.backendUrl = new ConfigModule().provideBackendUrl();
        this.gson = new GsonModule().provideGson();
    }

    @Before
    public void setupTestContent() {
        this.payload = this.gson.toJson(new HashMap<String, String>() {{
            put("name", "Test Name");
            put("value", "Comment");
            put("location", "Finland");
        }}, Map.class);
    }

    @Test
    public void testCompletableFuture() {
        AtomicBoolean done = new AtomicBoolean(false);
        AtomicReference<Feedbacks> result = new AtomicReference<>();
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        RequestBuilder.get(this.requestQueue, this.backendUrl.buildUpon().appendPath("feedback").toString())
                .setCompletableFuture(completableFuture)
                .addToRequestQueue();

        completableFuture
                .thenApply(s -> gson.fromJson(s, Feedbacks.class))
                .thenAccept(s -> {
                    result.set(s);
                    done.set(true);
                })
                .exceptionally(throwable -> {
                    done.set(true);
                    return null;
                });

        await().untilTrue(done);

        assertNotNull(result.get());
    }

    @Test
    public void testPostRequest() {
        AtomicBoolean done = new AtomicBoolean(false);
        AtomicBoolean success = new AtomicBoolean(false);
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        RequestBuilder.post(this.requestQueue, this.backendUrl.buildUpon().appendPath("feedbacks").toString())
                .setJsonAsBody(this.payload)
                .setCompletableFuture(completableFuture)
                .addToRequestQueue();

        completableFuture
                .thenApply(s -> this.gson.fromJson(s, Feedback.class))
                .handle((s, throwable) -> {
                    done.set(true);
                    return null;
                }).exceptionally(throwable -> {
                    return null;
            });

        await().untilTrue(done);

        assertTrue(success.get());
    }

    @Test
    public void testDeleteRequest() {
        AtomicBoolean done = new AtomicBoolean(false);
    }

    @Test
    public void testUpdateRequest() {

    }

    @Test
    public void testGetAllRequest() {
        AtomicBoolean done = new AtomicBoolean(false);
        AtomicBoolean success = new AtomicBoolean(false);

        new RequestBuilder(this.requestQueue, this.backendUrl.buildUpon().appendPath("feedback").toString())
                .setContentTypeJson()
                .setResponseListener(response -> {
                    done.set(true);
                    success.set(true);
                })
                .setErrorListener(error -> {
                    done.set(true);
                })
                .addToRequestQueue();

        await().untilTrue(done);

        assertTrue(success.get());
    }
}
