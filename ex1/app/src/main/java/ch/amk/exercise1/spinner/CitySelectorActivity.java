package ch.amk.exercise1.spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Visibility;
import ch.amk.exercise1.App;
import ch.amk.exercise1.PostActivity;
import ch.amk.exercise1.R;
import ch.amk.exercise1.models.openweather.OpenWeather;
import ch.amk.exercise1.service.OpenWeatherManager;
import ch.amk.exercise1.utils.ExceptionBox;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class CitySelectorActivity extends AppCompatActivity {

    private static final List<String> CITY_LIST = Arrays.asList(
            "Rovaniemi, fi",
            "London, en",
            "Zug, ch",
            "Helsinki, fi",
            "Bern, ch"
    );

    private Spinner spinner;

    private ProgressBar progressBar;

    @Inject
    OpenWeatherManager openWeatherManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_city_selector);

        this.spinner = (Spinner) this.findViewById(R.id.city_spinner);
        this.progressBar = (ProgressBar) this.findViewById(R.id.city_loader_bar);

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

    public void loadCity(String city) {
        this.showUiElement(this.progressBar);

        this.openWeatherManager.get(city, response -> {
            this.showCityInformation(response);

            //this.hideUiElement(this.progressBar);
        }, error -> {
            new ExceptionBox(error).show(this);
        });
    }

    private void toggleHiddenUiElement(final android.view.View uiElement, int visibility) {
        this.runOnUiThread(() -> uiElement.setVisibility(visibility));
    }

    private void showUiElement(final View uiElement) {
        this.toggleHiddenUiElement(uiElement, View.VISIBLE);
    }

    private void hideUiElement(final View uiElement) {
        this.toggleHiddenUiElement(uiElement, View.INVISIBLE);
    }

    private void showCityInformation(OpenWeather information) {
        this.showUiElement(this.findViewById(R.id.data_view));
    }

}
