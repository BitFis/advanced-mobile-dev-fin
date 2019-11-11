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

public class PostActivity extends Activity {

    public static final String ENDPOINT = "https://kylewbanks.com/rest/posts.json";

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.post_activity);

        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }

    public void fetchPost(String endpoint) {
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
    };
    private final Response.ErrorListener onError = (VolleyError error) -> {
        Log.e("PostActivity", error.toString());
        error.printStackTrace();

        this.runOnUiThread(() -> {
            PopupWindow popup = new PopupWindow(this);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setTitle("Error occured during request")
                    .setMessage(error.toString());

            alertDialogBuilder.create().show();
        });
    };

}
