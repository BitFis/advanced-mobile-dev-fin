package ch.amk.exercise3.api;

import androidx.appcompat.app.AppCompatActivity;
import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.service.FeedbackService;
import dagger.android.AndroidInjection;

import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    FeedbackService feedbackService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
