package ch.amk.exercise3.api;

import androidx.appcompat.app.AppCompatActivity;
import ch.amk.exercise3.api.service.TestService;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    TestService testService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = this.findViewById(R.id.text_box);
        textView.setText(testService.getText());
    }
}
