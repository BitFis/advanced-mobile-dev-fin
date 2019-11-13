package ch.amk.exercise1.spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ch.amk.exercise1.R;
import ch.amk.exercise1.TextEntryPair;
import ch.amk.exercise1.models.openweather.OpenWeather;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

public class OpenWeatherDetailActivity extends AppCompatActivity {

    private static final HashMap<String, Function<OpenWeather, String>> DETAILS_INFORMATION = new HashMap<String, Function<OpenWeather, String>>() {{
        put("Tempreture:", openWeather -> openWeather.getMain().getTemp() + " ℃");
        put("Wind speed:", openWeather -> openWeather.getWind().getSpeed().toString());
        put("Humidity:", openWeather -> openWeather.getMain().getHumidity().toString());
    }};

    public static final String OPENWEATHER_PARCEL_KEY = "openWeather";

    private OpenWeather openWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_weather_detail);

        this.openWeather = this.getIntent().getParcelableExtra(OPENWEATHER_PARCEL_KEY);

        if(Objects.isNull(this.openWeather)) {
            throw new RuntimeException("openWeather is not as extra added to the activity!!!");
        }

        this.addInformationToView(OpenWeatherDetailActivity.DETAILS_INFORMATION, openWeather);
        this.runOnUiThread(() -> ((TextView)this.findViewById(R.id.detail_city_name)).setText(this.openWeather.getName()));
    }

    private void addInformationToView(final HashMap<String, Function<OpenWeather, String>> details, final OpenWeather openWeather) {
        LinearLayout fragContainer = this.findViewById(R.id.weather_detail_container);

        FragmentManager fragMan = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragMan.beginTransaction();
        try {
            details.forEach((s, openWeatherStringFunction) -> transaction.add(
                    fragContainer.getId(),
                    TextEntryPair.newInstance(s, openWeatherStringFunction.apply(openWeather))
            ));
        } finally {
            transaction.commit();
        }
    }
}
