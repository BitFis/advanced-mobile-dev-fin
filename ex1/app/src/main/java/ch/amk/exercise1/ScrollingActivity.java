package ch.amk.exercise1;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import javax.inject.Inject;

import ch.amk.exercise1.models.Post;
import ch.amk.exercise1.ui.PostContent;

public class ScrollingActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    public static final String ENDPOINT = "https://kylewbanks.com/rest/posts.json";

    @Inject
    protected RequestQueue requestQueue;

    @Inject protected Gson gson;

    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        if (recyclerViewAdapter == null) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_fragment);
            recyclerView = (RecyclerView) currentFragment.getView();
            recyclerViewAdapter = ((RecyclerView) currentFragment.getView()).getAdapter();
        }

        ((App)this.getApplication())
                .getComponent()
                .inject(ScrollingActivity.this);


/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/    }

    @Override
    public void onListFragmentInteraction(Post item) {

    }
}
