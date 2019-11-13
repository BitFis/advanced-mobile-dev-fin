package ch.amk.exercise1.spinner;

import androidx.appcompat.app.AppCompatActivity;
import ch.amk.exercise1.App;
import ch.amk.exercise1.PostActivity;
import ch.amk.exercise1.R;
import ch.amk.exercise1.service.OpenWeatherManager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class CitySelectorActivity extends AppCompatActivity {

    private static final String WHEATER_API_KEY = "01f4ce4de22786346799bfec88c18db8";

    private static final List<String> CITY_LIST = Arrays.asList(
            "Rovaniemi, fi",
            "London, en",
            "Zug, ch",
            "Helsinki, fi",
            "Bern, ch"
    );

    private Spinner spinner;

    @Inject
    OpenWeatherManager openWeatherManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_city_selector);

        this.spinner = (Spinner) this.findViewById(R.id.city_spinner);

        ((App)this.getApplication())
                .getComponent()
                .inject(CitySelectorActivity.this);

        this.initSpinner(this.spinner);
    }

    private void initSpinner(Spinner spinner) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                CITY_LIST);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
