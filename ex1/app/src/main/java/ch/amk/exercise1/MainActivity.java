package ch.amk.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ch.amk.exercise1.modules.Main;
import ch.amk.exercise1.spinner.CitySelectorActivity;
import dagger.Component;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.openPostActivity)
                .setOnClickListener(this.getOpenActivityIntent(PostActivity.class));
        this.findViewById(R.id.openSpinnerExerciseActivity)
                .setOnClickListener(this.getOpenActivityIntent(CitySelectorActivity.class));

    }

    private View.OnClickListener getOpenActivityIntent(Class<?> activity) {
        return v -> {
            Intent intent = new Intent(this, activity);
            this.startActivity(intent);
        };
    }
}
