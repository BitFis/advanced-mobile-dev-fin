package com.example.ex2.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import com.example.ex2.R;
import com.example.ex2.models.openweather.OpenWeatherForcast;
import com.example.ex2.models.openweather.Weather;
import com.example.ex2.modules.TimeModule;
import com.example.ex2.service.OpenWeatherService;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WeatherPopupAdapter implements GoogleMap.InfoWindowAdapter {

    private ComponentActivity context;
    private OpenWeatherService service;
    private Marker selectedMarker;

    private NumberFormat decimalFormat = new DecimalFormat("#0.00");

    private Map<String, String> content = new LinkedHashMap<>();
    private Map<String, String> forcast = new LinkedHashMap<>();
    private String title = null;

    public WeatherPopupAdapter(ComponentActivity context, OpenWeatherService service) {
        this.context = context;
        this.service = service;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    private LinearLayout addEntry(String label, String content) {
        LinearLayout ll = new LinearLayout(this.context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView l = new TextView(this.context);
        l.setText(label);
        l.setTextColor(Color.BLACK);
        l.setPadding(0,0,20,0);

        TextView c = new TextView(context);
        c.setText(content);
        c.setTextColor(Color.GRAY);

        ll.addView(l);
        ll.addView(c);

        return ll;
    }

    private void loadWeatherData(Marker marker) {
        // load weather data
        this.service.getLatLng(this.selectedMarker.getPosition(), response -> {
            DateTimeFormatter dateTimeFormatter = new TimeModule().provideTime();

            ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofTotalSeconds(response.getTimezone()));

            // temperature, humidity, wind speed, sun rise and sun set
            this.content.put("temperature", response.getMain().getTemp() + "º");
            this.content.put("humidity", String.valueOf(response.getMain().getHumidity()));
            this.content.put("wind speed", decimalFormat.format(response.getWind().getSpeed()));
            this.content.put("sun rise ", dateTimeFormatter.withZone(zoneId).format(Instant.ofEpochSecond(response.getSys().getSunrise())));
            this.content.put("sun set", dateTimeFormatter.withZone(zoneId).format(Instant.ofEpochSecond(response.getSys().getSunset())));

            this.title = response.getName();

            this.context.runOnUiThread(() -> this.selectedMarker.showInfoWindow());
        }, error -> {
            Log.e("WeatherPopupAdapter", "error occured during current weather loading", error);
        });

        this.service.getForcastLatLng(this.selectedMarker.getPosition(), response -> {
            AtomicInteger countForcast = new AtomicInteger(3);

            response.groupByDayMerge(OpenWeatherForcast::mergeAvrages).forEach((date, data) -> {
                if(countForcast.getAndDecrement() > 0) {
                    this.forcast.put(date, this.decimalFormat.format(data.getMain().getTemp()) + "º");
                }
            });

            this.context.runOnUiThread(() -> this.selectedMarker.showInfoWindow());
        }, error -> {
            Log.e("WeatherPopupAdapter", "error occured during forcast loading", error);
        });
    }

    private TextView getTitle(String title) {
        TextView titleView = new TextView(this.context);
        titleView.setTextColor(Color.BLACK);
        titleView.setGravity(Gravity.CENTER);
        titleView.setTypeface(null, Typeface.BOLD);
        titleView.setText(title);

        return titleView;
    }

    private TextView getSnippet(String snippet) {
        TextView snippetView = new TextView(this.context);
        snippetView.setTextColor(Color.GRAY);
        snippetView.setText(snippet);

        return snippetView;
    }

    /**
     * The markers lat/long will be used to get the weather data
     * for the location
     *
     * @param marker Google marker
     * @return View with weather data
     */
    @Override
    public View getInfoContents(Marker marker) {
        LinearLayout info = new LinearLayout(this.context);
        info.setOrientation(LinearLayout.VERTICAL);

        if(!marker.equals(this.selectedMarker)) {
            // new marker, reset content
            this.content = new LinkedHashMap<>();
            this.forcast = new LinkedHashMap<>();

            info.addView(this.getTitle(marker.getTitle()));
            info.addView(this.getSnippet("Weather Data Loading ..."));

            this.selectedMarker = marker;
            this.loadWeatherData(this.selectedMarker);
        } else {
            info.addView(this.getTitle(this.title));
            this.content.forEach((s, s2) -> info.addView(this.addEntry(s, s2)));
            info.addView(this.getSnippet(""));
            this.forcast.forEach((s, s2) -> info.addView(this.addEntry(s, s2)));
        }

        return info;
    }
}
