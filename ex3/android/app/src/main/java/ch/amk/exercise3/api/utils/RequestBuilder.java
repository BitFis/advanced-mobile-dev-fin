package ch.amk.exercise3.api.utils;

import android.net.Uri;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyLog;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class RequestBuilder {
    private final static String TAG = "RequestBuilder";

    private String url;
    private int httpMethod;
    private String contentType;
    private RequestQueue requestQueue;
    private Response.ErrorListener errorListener = error -> {
        Log.e(TAG, "Error occureding during request", error);
    };
    private Response.Listener<String> responseListener;
    private Request.Priority priority;
    private RetryPolicy retryPolicy;

    private int retryTimeout = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
    private int retryMaxRetries = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;
    private float retryBackoffMult = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

    private HashMap<String, String> headers = new HashMap<String, String>();
    private String body;
    private boolean shouldCache;

    public RequestBuilder(RequestQueue requestQueue, String url) {
        this.requestQueue = requestQueue;
        this.url = url;
        this.useGet()
            .setNormalPriority()
            .shouldCache()
            .acceptAllContentTypes();
    }

    public static RequestBuilder get(RequestQueue requestQueue, String url) {
        return new RequestBuilder(requestQueue, url);
    }

    public static RequestBuilder post(RequestQueue requestQueue, String url) {
        return new RequestBuilder(requestQueue, url)
                .usePost();
    }

    public static RequestBuilder delete(RequestQueue requestQueue, String url) {
        return new RequestBuilder(requestQueue, url)
                .useDelete();
    }

    public static RequestBuilder put(RequestQueue requestQueue, String url) {
        return new RequestBuilder(requestQueue, url)
                .usePut();
    }

    public RequestBuilder putHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public RequestBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public RequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public RequestBuilder setCompletableFuture(CompletableFuture<String> completableFuture) {
        this.setErrorListener(completableFuture::completeExceptionally);
        this.setResponseListener(completableFuture::complete);
        return this;
    }

    /**
     * Set request body and change content type to json
     *
     * @param json will replace body
     */
    public RequestBuilder setJsonAsBody(String json) {
        setBody(json);
        setContentTypeJson();
        return this;
    }

    public RequestBuilder setAcceptContentType(String accept) {
        this.putHeader("Accept", accept);
        return this;
    }

    public RequestBuilder acceptAllContentTypes() {
        return this.setAcceptContentType("*/*");
    }

    public RequestBuilder setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public RequestBuilder setContentTypeJson() {
        return setContentType("application/json");
    }

    public RequestBuilder setErrorListener(Response.ErrorListener errorListener) {
        this.errorListener = errorListener;
        return this;
    }

    public RequestBuilder setResponseListener(Response.Listener<String> responseListener) {
        this.responseListener = responseListener;
        return this;
    }

    public RequestBuilder setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    public RequestBuilder setRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier) {
        this.retryPolicy = new DefaultRetryPolicy(initialTimeoutMs, maxNumRetries, backoffMultiplier);
        return this;
    }

    public com.android.volley.Request<String> addToRequestQueue() {
        Request request = buildRequest();
        return requestQueue.add(request);
    }

    private Request buildRequest() {
        Request request = new Request(httpMethod, url, responseListener, errorListener);
        request.headers = headers;
        if (contentType != null)
            request.setConntentType(contentType);
        request.setBody(body);
        if ((retryPolicy == null))//&& ((retryTimeout ) || (retryMaxRetries) || (retryBackoffMult))
            request.setRetryPolicy(new DefaultRetryPolicy(retryTimeout, retryMaxRetries, retryBackoffMult));
        else
            request.setRetryPolicy(retryPolicy);

        request.setShouldCache(shouldCache);
        VolleyLog.d("RequestBuilt %s %s", url, body);
        return request;
    }

    public RequestBuilder setTimeout(int retryTimeoutMilliseconds) {
        this.retryTimeout = retryTimeoutMilliseconds;
        return this;
    }

    public RequestBuilder setMaxRetries(int retryMaxRetries) {
        this.retryMaxRetries = retryMaxRetries;
        return this;
    }

    // sets for http methods
    public RequestBuilder useGet() {
        httpMethod = Request.Method.GET;
        return this;
    }

    public RequestBuilder usePost() {
        httpMethod = Request.Method.POST;
        return this;
    }

    public RequestBuilder usePut() {
        httpMethod = Request.Method.PUT;
        return this;
    }

    public RequestBuilder useDelete() {
        httpMethod = Request.Method.DELETE;
        return this;
    }

    public RequestBuilder useHead() {
        httpMethod = Request.Method.HEAD;
        return this;
    }

    public RequestBuilder useOptions() {
        httpMethod = Request.Method.OPTIONS;
        return this;
    }

    public RequestBuilder useTrace() {
        httpMethod = Request.Method.TRACE;
        return this;
    }

    public RequestBuilder usePatch() {
        httpMethod = Request.Method.PATCH;
        return this;
    }

    // set for Priority
    public RequestBuilder setLowPriority() {
        priority = Request.Priority.LOW;
        return this;
    }

    public RequestBuilder setNormalPriority() {
        priority = Request.Priority.NORMAL;
        return this;
    }

    public RequestBuilder setHighPriority() {
        priority = Request.Priority.HIGH;
        return this;
    }

    public RequestBuilder setImmediatePriority() {
        priority = Request.Priority.IMMEDIATE;
        return this;
    }

    // set for cache
    public RequestBuilder shouldCache() {
        shouldCache = true;
        return this;
    }

    public RequestBuilder shouldNotCache() {
        shouldCache = false;
        return this;
    }

    private static String mapToStr(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry entry : map.entrySet())
            builder.append(entry.getKey()).append(entry.getValue());
        return builder.toString();
    }
}

