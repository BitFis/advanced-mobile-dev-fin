package ch.amk.exercise3.api.utils;

import android.net.Uri;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import net.bytebuddy.implementation.bytecode.Throw;

import org.awaitility.Awaitility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

    private String getFeedbackUrl() {
        return this.backendUrl.buildUpon().appendPath("feedback").toString();
    }

    private String getFeedbackUrl(int id) {
        return this.backendUrl.buildUpon()
                .appendPath("feedback")
                .appendPath(String.valueOf(id)).toString();
    }

    @Test
    public void testCompletableFuture() {
        AtomicBoolean done = new AtomicBoolean(false);
        AtomicReference<Feedbacks> result = new AtomicReference<>();
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        RequestBuilder.get(this.requestQueue, this.getFeedbackUrl())
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

    private Feedback createTestFeedbackInDB() {
        AtomicBoolean done = new AtomicBoolean(false);
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        AtomicReference<Feedback> feedback = new AtomicReference<>();

        RequestBuilder.post(this.requestQueue, this.getFeedbackUrl())
                .setJsonAsBody(this.payload)
                .setCompletableFuture(completableFuture)
                .addToRequestQueue();

        completableFuture
                .thenApply(s -> this.gson.fromJson(s, Feedback.class))
                .thenAccept(feedback::set)
                .handle((s, throwable) -> {
                    done.set(true);
                    return null;
                });

        await().untilTrue(done);
        return feedback.get();
    }

    @Test
    public void testPostRequest() {
        Feedback feedback = this.createTestFeedbackInDB();

        assertNotNull(feedback);
        assertEquals("Test Name", feedback.getName());
        assertEquals("Comment", feedback.getValue());
        assertEquals("Finland", feedback.getLocation());
    }

    @Test
    public void testDeleteRequest() {
        int id = this.createTestFeedbackInDB().getId();

        AtomicBoolean done = new AtomicBoolean(false);
        AtomicReference<Throwable> throwableReferecence = new AtomicReference<>();
        CompletableFuture<String> completableFutureDelete = new CompletableFuture<>();

        RequestBuilder.delete(this.requestQueue, this.getFeedbackUrl(id))
                .setCompletableFuture(completableFutureDelete)
                .addToRequestQueue();

        completableFutureDelete
                .handle((s, throwable) -> {
                    done.set(true);
                    throwableReferecence.set(throwable);
                    return null;
                });

        await().untilTrue(done);

        assertNull(throwableReferecence.get());
    }

    @Test
    public void testUpdateRequest() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        AtomicBoolean done = new AtomicBoolean(false);
        AtomicReference<Throwable> throwableAtomicReference = new AtomicReference<>();
        int id = this.createTestFeedbackInDB().getId();

        RequestBuilder.put(this.requestQueue, this.getFeedbackUrl(id))
                .setCompletableFuture(completableFuture)
                .setJsonAsBody(this.gson.toJson(new HashMap<String, String>() {{
                    put("name", "new Name");
                    put("value", "new Comment");
                    put("location", "new Location");
                }}, Map.class))
                .addToRequestQueue();

        completableFuture
            .thenApply(s -> this.gson.fromJson(s, Feedback.class))
            .thenAccept(s -> {
                assertEquals("new Name", s.getName());
                assertEquals("new Comment", s.getValue());
                assertEquals("new Location", s.getLocation());
            })
            .handle((s, throwable) -> {
                throwableAtomicReference.set(throwable);
                done.set(true);
                return null;
            });

        await().untilTrue(done);

        assertNull(throwableAtomicReference.get());
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
