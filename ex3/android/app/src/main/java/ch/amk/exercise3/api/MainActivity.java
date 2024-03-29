package ch.amk.exercise3.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ch.amk.exercise3.api.app.VolleyModule;
import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.models.Feedbacks;
import ch.amk.exercise3.api.service.FeedbackService;
import ch.amk.exercise3.api.ui.FeedbackItem;
import ch.amk.exercise3.api.utils.ExceptionBox;
import dagger.android.AndroidInjection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.ClientError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.drag.ItemTouchCallback;
import com.mikepenz.fastadapter.drag.SimpleDragCallback;
import com.mikepenz.fastadapter.swipe.SimpleSwipeCallback;
import com.mikepenz.fastadapter.swipe_drag.SimpleSwipeDragCallback;
import com.mikepenz.iconics.IconicsColor;
import com.mikepenz.iconics.IconicsColorList;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.IconicsSize;
import com.mikepenz.iconics.typeface.library.materialdesigniconic.MaterialDesignIconic;

import org.apache.commons.lang3.NotImplementedException;

import java.io.PipedOutputStream;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.meta.When;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ItemTouchCallback, SimpleSwipeCallback.ItemSwipeCallback {

    private static String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private FastAdapter fastAdapter;
    private ItemAdapter itemAdapter;

    //drag & drop
    private ItemTouchHelper touchHelper;
    private SimpleDragCallback touchCallback;

    private FloatingActionButton fabAdd;

    @Inject
    FeedbackService feedbackService;

    @Inject
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.itemAdapter = new ItemAdapter<FeedbackItem>();
        this.fastAdapter = FastAdapter.with(itemAdapter);

        this.recyclerView = (RecyclerView) this.findViewById(R.id.feedback_list);
        this.recyclerView.setAdapter(this.fastAdapter);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());

        this.fabAdd = this.findViewById(R.id.button_add);

        this.setupFeedbackList();
        this.setupAddButton();

        this.loadFeedbacks();
    }

    private void setupAddButton() {
        this.fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, FeedbackFormActivity.class);
            this.startActivityForResult(intent, Activity.RESULT_FIRST_USER);
        });

        this.fabAdd.setImageDrawable(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_plus)
                .color(IconicsColor.colorInt(Color.WHITE))
                .size(IconicsSize.dp(24)));
    }

    private void setupFeedbackList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        this.fastAdapter.notifyAdapterDataSetChanged();

        this.fastAdapter.setOnClickListener((view, iAdapter, o, integer) -> this.showFeedback(((FeedbackItem)o).get()));

        // setup swip to delete
        IconicsDrawable gmiDeleteDrawable = new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_delete)
                .color(IconicsColor.colorInt(Color.WHITE))
                .size(IconicsSize.dp(24));

        this.touchCallback = new SimpleSwipeDragCallback(
                this,
                this,
                gmiDeleteDrawable,
                ItemTouchHelper.RIGHT,
                getColor(android.R.color.holo_red_dark)
        )
                .withBackgroundSwipeRight(getColor(android.R.color.holo_red_dark))
                .withLeaveBehindSwipeRight(gmiDeleteDrawable);

        this.touchHelper = new ItemTouchHelper(touchCallback);
        touchHelper.attachToRecyclerView(this.recyclerView);
    }

    private boolean showFeedback(Feedback feedback) {
        Intent intent = new Intent(this, FeedbackFormActivity.class);

        intent.putExtra(FeedbackFormActivity.INTENT_EXTRA_ATTR_FEEDBACK, feedback);

        this.startActivityForResult(intent, Activity.RESULT_FIRST_USER);

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null) {
            Feedback feedback = data.getParcelableExtra(FeedbackFormActivity.INTENT_EXTRA_ATTR_FEEDBACK);

            int position = this.itemAdapter.getAdapterPosition(feedback.getId());

            if(position >= 0) {
                // update item
                ((FeedbackItem)this.itemAdapter.getAdapterItem(position)).set(feedback);
                this.fastAdapter.notifyAdapterItemChanged(position);
            } else {
                // insert new item
                this.itemAdapter.add(FeedbackItem.from(feedback));
            }
        }
    }

    private void loadFeedbacks() {
        this.feedbackService.getAll()
            .thenAccept(feedbacks -> {
                this.runOnUiThread(() -> {
                    this.itemAdapter.add(FeedbackItem.from(
                            feedbacks.getEmbedded().getFeedback()
                    ));
                    this.fastAdapter.notifyAdapterDataSetChanged();
                });
            }).exceptionally(throwable -> {
                new ExceptionBox(throwable).show(this);
                throwable.printStackTrace();
                return null;
            });
    }

    @Override
    public void itemTouchDropped(int i, int i1) {

    }

    @Override
    public boolean itemTouchOnMove(int i, int i1) {
        return false;
    }

    @Override
    public void itemSwiped(int i, int i1) {
        this.feedbackService.delete((int)this.itemAdapter.getAdapterItem(i).getIdentifier());
        this.itemAdapter.remove(i);
    }
}
