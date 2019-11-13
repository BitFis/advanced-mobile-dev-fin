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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class CitySelectorActivity extends AppCompatActivity {

    private static final List<String> CITY_LIST = Arrays.asList(
            "No City Selected",
            "Rovaniemi, fi",
            "London, en",
            "Zug, ch",
            "Helsinki, fi",
            "Bern, ch"
    );

    private Spinner spinner;

    private ProgressBar progressBar;
    private OpenWeather selectedWeather;
    private TextView textTemp;
    private View resultView;

    @Inject
    OpenWeatherManager openWeatherManager;

    private AdapterView.OnItemSelectedListener onSpinnerChange = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position <= 0) {
                CitySelectorActivity.this.hideUiElement(CitySelectorActivity.this.resultView);
            } else {
                CitySelectorActivity.this.loadCity(CitySelectorActivity.CITY_LIST.get(position));
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            CitySelectorActivity.this.hideUiElement(CitySelectorActivity.this.resultView);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_city_selector);

        this.spinner = (Spinner) this.findViewById(R.id.city_spinner);
        this.progressBar = (ProgressBar) this.findViewById(R.id.city_loader_bar);
        this.textTemp = (TextView)this.findViewById(R.id.data_temp);
        this.resultView = this.findViewById(R.id.data_view);

        this.spinner.setOnItemSelectedListener(this.onSpinnerChange);

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

    private void loadCity(String city) {
        this.showUiElement(this.progressBar);
        this.hideUiElement(this.findViewById(R.id.info_choose_city));

        this.openWeatherManager.get(city, response -> {
            this.selectedWeather = response;
            this.showCityInformation(this.selectedWeather);

            this.hideUiElement(this.progressBar);
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
        this.runOnUiThread(() -> this.textTemp.setText(information.getMain().getTemp() + " ℃"));

        this.showUiElement(this.resultView);
    }

}
