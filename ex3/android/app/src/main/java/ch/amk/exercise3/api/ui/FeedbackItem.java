package ch.amk.exercise3.api.ui;

import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import ch.amk.exercise3.api.R;
import ch.amk.exercise3.api.models.Feedback;

public class FeedbackItem extends AbstractItem<FeedbackItem.ViewHolder> {

    private Feedback feedback;

    private FeedbackItem(Feedback item){
        this.feedback = item;
        this.setIdentifier(item.getId());
    }

    public static FeedbackItem from (Feedback feedback) {
        return new FeedbackItem(feedback);
    }

    public static List<FeedbackItem> from(Collection<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackItem::from).collect(Collectors.toList());
    }

    public Feedback get() {
        return this.feedback;
    }

    public void set(Feedback feedback) {
        this.feedback = feedback;
    }

    @NotNull
    @Override
    public ViewHolder getViewHolder(@NotNull View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.feedback_item;
    }

    @Override
    public int getType() {
        return feedback.getId();
    }

    class ViewHolder extends FastAdapter.ViewHolder<FeedbackItem> {
        private TextView name;
        private TextView content;

        ViewHolder(@NotNull View view) {
            super(view);
            this.name = view.findViewById(R.id.material_drawer_name);
            this.content = view.findViewById(R.id.material_drawer_description);
        }

        @Override
        public void bindView(@NotNull FeedbackItem item, @NotNull List<Object> list) {
            this.name.setText(item.feedback.getName());
            this.content.setText(item.feedback.getValue());
        }

        @Override
        public void unbindView(@NotNull FeedbackItem item) {
            // remove info
        }
    }

}
