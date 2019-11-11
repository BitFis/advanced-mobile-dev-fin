package ch.amk.exercise1;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import ch.amk.exercise1.models.Post;
import ch.amk.exercise1.utils.ExceptionBox;

public class PostActivity extends Activity {

    public static final String ENDPOINT = "https://kylewbanks.com/rest/posts.json";

    @Inject protected RequestQueue requestQueue;

    @Inject protected Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.post_activity);

        ((App)this.getApplication())
                .getComponent()
                .inject(PostActivity.this);

        this.fetchPost(this.ENDPOINT);
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }

    private void fetchPost(String endpoint) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                endpoint,
                onPostLoaded,
                onError
        );

        requestQueue.add(request);
    }

    private final Response.Listener<String> onPostLoaded = (String response) -> {
        Log.i("PostActivity", response);

        List<Post> posts = Arrays.asList(gson.fromJson(response, Post[].class));
    };
    private final Response.ErrorListener onError = (VolleyError error) -> {
        Log.e("PostActivity", error.toString());

        new ExceptionBox(error).show(this);
    };

}
