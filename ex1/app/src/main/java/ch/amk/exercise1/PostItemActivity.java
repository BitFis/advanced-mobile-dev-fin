package ch.amk.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PostItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item);

        Bundle bundle = this.getIntent().getExtras();

    }
}