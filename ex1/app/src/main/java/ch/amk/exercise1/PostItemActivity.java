package ch.amk.exercise1;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import ch.amk.exercise1.models.Post;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PostItemActivity extends AppCompatActivity {

    public static final String POST_ATTRIBUTE = "post";

    private Post item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item);

        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy hh:mm:ss");

        this.item = this.getIntent().getParcelableExtra(this.POST_ATTRIBUTE);

        this.setTextViewText(R.id.post_author, this.item.Author);
        this.setTextViewText(R.id.post_body, this.item.Body);
        this.setTextViewText(R.id.post_created,  dateFormat.format(this.item.DateCreated));
        this.setTextViewText(R.id.post_title, this.item.Title);
        this.setTextViewText(R.id.post_url, this.item.Url);
    }

    private void setTextViewText(@IdRes int id, String text) {
        ((TextView)this.findViewById(id)).setText(text);
    }
}
