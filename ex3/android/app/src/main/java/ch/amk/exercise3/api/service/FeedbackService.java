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

    public RequestFuture<Feedback> get(int id) {
        throw new NotImplementedException("not implemented yet");
    }

    private CompletableFuture createRequest(
            int method,
            String url,
            Consumer<String> success,
            CompletableFuture completableFuture) {
        return createRequest(
                method,
                url,
                success,
                completableFuture,
                new HashMap<>()
        );
    }

    private CompletableFuture createRequest(
            int method,
            String url,
            Consumer<String> success,
            CompletableFuture completableFuture,
            Map<String, String> parameters) {
        this.requestQueue.add(new StringRequest(
                method,
                url,
                response -> {
                    try {
                        success.accept(response);
                    } catch (Throwable ex) {
                        completableFuture.completeExceptionally(ex);
                    }
                },
                completableFuture::completeExceptionally
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String>  params = new HashMap<String, String>();
                params.put("Accept", "*/*");

                if(method != Method.GET) {
                    params.put("Content-Type", "application/json");
                }

                return params;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return FeedbackService.this.gson.toJson(parameters, Map.class).getBytes();
            }
        });

        return completableFuture;
    }

    public CompletableFuture<Feedbacks> getAll() {
        CompletableFuture<Feedbacks> completableFuture = new CompletableFuture<>();

        return this.createRequest(
                Request.Method.GET,
                this.getFeedbackPath(),
                s -> completableFuture.complete(this.gson.fromJson(s, Feedbacks.class)),
                completableFuture
        );
    }

    public void delete(int id) {
        throw new NotImplementedException("not implemented yet");
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

        CompletableFuture<Feedback> completableFuture = new CompletableFuture<>();

        return this.createRequest(
                Request.Method.POST,
                this.getFeedbackPath(),
                s -> completableFuture.complete(this.gson.fromJson(s, Feedback.class)),
                completableFuture,
                values
        );
    }
}
