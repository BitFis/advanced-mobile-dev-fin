package ch.amk.exercise1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;

public class PostActivity extends Activity {

    private static final String ENDPOINT = "https://kylewbanks.com/rest/posts.json";

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.post_activity);
    }
}
