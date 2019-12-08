package ch.amk.exercise3.api.service;

import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import javax.inject.Inject;
import javax.inject.Named;

import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.models.Feedbacks;
import ch.amk.exercise3.api.utils.RequestBuilder;

public class FeedbackService {

    private final static String FEEDBACK_PATH = "feedback";

    private final Uri backendUrl;
    private final RequestQueue requestQueue;
    private final Gson gson;

    @Inject
    public FeedbackService(
            @Named("backend_url") Uri backendUrl,
            RequestQueue requestQueue,
            Gson gson) {
        this.backendUrl = backendUrl;
        this.requestQueue = requestQueue;
        this.gson = gson;
    }

    private Uri.Builder feedbackPath() {
        return this.backendUrl.buildUpon()
                .appendPath(FEEDBACK_PATH);
    }

    private String getFeedbackPath() {
        return this.feedbackPath().toString();
    }

    private String getFeedbackPath(int id) {
        return this.feedbackPath().appendPath(String.valueOf(id))
                .toString();
    }

    public CompletableFuture<Feedback> get(int id) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        RequestBuilder.get(this.requestQueue, this.getFeedbackPath(id))
                .setCompletableFuture(completableFuture)
                .addToRequestQueue();

        return completableFuture.thenApply(s -> this.gson.fromJson(s, Feedback.class));
    }

    public CompletableFuture<Feedbacks> getAll() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        RequestBuilder.get(this.requestQueue, this.getFeedbackPath())
                .setCompletableFuture(completableFuture)
                .addToRequestQueue();

        return completableFuture
                .thenApply(s -> this.gson.fromJson(s, Feedbacks.class));
    }

    public CompletableFuture<String> delete(int id) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        RequestBuilder.delete(this.requestQueue, this.getFeedbackPath(id))
                .setCompletableFuture(completableFuture)
                .addToRequestQueue();

        return completableFuture;
    }

    /**
     * Creates a new feedback entry if the id is <= 0 otherwise
     * will update the feedback
     * @param feedback
     * @return
     */
    public CompletableFuture<Feedback> save(Feedback feedback) {
        Map<String, String> values = new HashMap<String, String>() {{
            put("name", feedback.getName());
            put("value", feedback.getValue());
            put("location", feedback.getLocation());
        }};

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        RequestBuilder requestBuilder = new RequestBuilder(this.requestQueue, this.getFeedbackPath())
                .setJsonAsBody(this.gson.toJson(values, Map.class))
                .setCompletableFuture(completableFuture);

        if(feedback.getId() > 0) {
            requestBuilder
                    .usePut()
                    .setUrl(this.getFeedbackPath(feedback.getId()));
        } else {
            requestBuilder
                    .usePost();
        }

        requestBuilder.addToRequestQueue();

        return completableFuture.thenApply(s -> this.gson.fromJson(s, Feedback.class));
    }
}
