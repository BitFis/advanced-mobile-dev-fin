package ch.amk.exercise3.api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.service.FeedbackService;
import ch.amk.exercise3.api.ui.FeedbackItem;
import dagger.android.AndroidInjection;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

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

import java.util.Base64;

import javax.annotation.meta.When;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ItemTouchCallback, SimpleSwipeCallback.ItemSwipeCallback {

    private RecyclerView recyclerView;
    private FastAdapter fastAdapter;
    private ItemAdapter itemAdapter;

    //drag & drop
    private ItemTouchHelper touchHelper;
    private SimpleDragCallback touchCallback;

    private FloatingActionButton fabAdd;

    @Inject
    FeedbackService feedbackService;

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
    }

    private void setupAddButton() {
        this.fabAdd.setOnClickListener(v -> {

        });

        this.fabAdd.setImageDrawable(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_plus)
                .color(IconicsColor.colorInt(Color.WHITE))
                .size(IconicsSize.dp(24)));
    }

    private void setupFeedbackList() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FeedbackItem item = new FeedbackItem(new Feedback().withId(1)
                .withName("Lucien Zuercher")
                .withValue("Some one"));
        FeedbackItem item2 = new FeedbackItem(new Feedback().withId(2).withName("Hello World").withValue("Content"));

        this.itemAdapter.add(item);
        this.itemAdapter.add(item2);

        this.fastAdapter.notifyAdapterDataSetChanged();

        this.fastAdapter.setOnClickListener((view, iAdapter, o, integer) -> {
            return false;
        });

        // setup swip to delete
        IconicsDrawable leaveBehindDrawableLeft = new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_delete)
                .color(IconicsColor.colorInt(Color.WHITE))
                .size(IconicsSize.dp(24));
        IconicsDrawable leaveBehindDrawableRigth = new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_archive)
                .color(IconicsColor.colorInt(Color.WHITE))
                .size(IconicsSize.dp(24));

        this.touchCallback = new SimpleSwipeDragCallback(
                this,
                this,
                leaveBehindDrawableRigth,
                ItemTouchHelper.LEFT,
                getColor(android.R.color.holo_blue_dark)
        )
                .withBackgroundSwipeRight(getColor(android.R.color.holo_red_dark))
                .withLeaveBehindSwipeRight(leaveBehindDrawableLeft);

        this.touchHelper = new ItemTouchHelper(touchCallback);
        touchHelper.attachToRecyclerView(this.recyclerView);
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

    }
}
