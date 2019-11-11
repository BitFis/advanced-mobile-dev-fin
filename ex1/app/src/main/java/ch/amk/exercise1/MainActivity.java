package ch.amk.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ch.amk.exercise1.modules.Main;
import dagger.Component;

public class MainActivity extends AppCompatActivity {

    private Button activityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activityButton = this.findViewById(R.id.openPostActivity);
        this.activityButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, PostActivity.class);
            this.startActivity(intent);
        });
    }
}
