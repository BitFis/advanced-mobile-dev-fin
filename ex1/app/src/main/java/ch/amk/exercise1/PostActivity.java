package ch.amk.exercise1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import ch.amk.exercise1.models.Post;
import ch.amk.exercise1.ui.PostContent;
import ch.amk.exercise1.utils.ExceptionBox;

public class PostActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    public static final String ENDPOINT = "https://kylewbanks.com/rest/posts.json";

    @Inject protected RequestQueue requestQueue;

    @Inject protected Gson gson;

    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.post_activity);

        if (recyclerViewAdapter == null) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_fragment);
            recyclerView = (RecyclerView) currentFragment.getView();
            recyclerViewAdapter = ((RecyclerView) currentFragment.getView()).getAdapter();
        }

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

    private void redraw() {
        this.runOnUiThread(() -> this.recyclerViewAdapter.notifyDataSetChanged());
    }

    private final Response.Listener<String> onPostLoaded = (String response) -> {
        Log.i("PostActivity", response);

        PostContent.ITEMS.addAll(Arrays.asList(gson.fromJson(response, Post[].class)));

        this.redraw();
    };
    private final Response.ErrorListener onError = (VolleyError error) -> {
        Log.e("PostActivity", error.toString());

        new ExceptionBox(error).show(this);
    };

    @Override 
    public void onListFragmentInteraction(Post item) {
        Intent intent = new Intent(this, PostItemActivity.class);
        intent.putExtra(PostItemActivity.POST_ATTRIBUTE, (Parcelable) item);
        this.startActivity(intent);
    }
}
