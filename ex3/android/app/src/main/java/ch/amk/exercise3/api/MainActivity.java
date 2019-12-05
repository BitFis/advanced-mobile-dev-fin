package ch.amk.exercise3.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.service.FeedbackService;
import ch.amk.exercise3.api.ui.FeedbackItem;
import dagger.android.AndroidInjection;

import android.os.Bundle;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FastAdapter fastAdapter;
    private ItemAdapter itemAdapter;

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FeedbackItem item = new FeedbackItem(new Feedback().withId(1)
                .withName("Lucien Zuercher")
                .withValue("Some one"));
        FeedbackItem item2 = new FeedbackItem(new Feedback().withId(2).withName("Hello World").withValue("Content"));

        this.itemAdapter.add(item);
        this.itemAdapter.add(item2);

        this.fastAdapter.notifyAdapterDataSetChanged();

    }


}
