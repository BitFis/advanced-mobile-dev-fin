package ch.amk.exercise3.api.service;

import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.lang3.NotImplementedException;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Named;

import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.models.Feedbacks;

public class FeedbackService {

    private final static String FEEDBACK_PATH = "feedback";

    private final String backendUrl;
    private final RequestQueue requestQueue;
    private final Gson gson;

    @Inject
    public FeedbackService(
            @Named("backend_url") String backendUrl,
            RequestQueue requestQueue,
            Gson gson) {
        this.backendUrl = backendUrl;
        this.requestQueue = requestQueue;
        this.gson = gson;
    }

    private String getFeedbackPath() {
        return Uri.parse(this.backendUrl).buildUpon()
                .appendPath(FEEDBACK_PATH).toString();
    }

    public RequestFuture<Feedback> get(int id) {
        throw new NotImplementedException("not implemented yet");
    }

    public CompletableFuture<Feedbacks> getAll() {
        CompletableFuture<Feedbacks> completableFuture = new CompletableFuture<>();

        String url = this.getFeedbackPath();

        StringRequest req = new StringRequest(
                Request.Method.GET,
                url,
                response -> {
                    try {
                        completableFuture.complete(this.gson.fromJson(response, Feedbacks.class));
                    } catch (JsonSyntaxException ex) {
                        completableFuture.completeExceptionally(ex);
                    }
                },
                completableFuture::completeExceptionally
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Accept", "*/*");

                return params;
            }
        };

        this.requestQueue.add(req);

        return completableFuture;
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
    public RequestFuture<Feedback> save(Feedback feedback) {
        throw new NotImplementedException("not implemented yet");
    }
}
