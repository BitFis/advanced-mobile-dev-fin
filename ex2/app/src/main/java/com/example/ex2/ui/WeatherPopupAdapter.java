package com.example.ex2.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ex2.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class WeatherPopupAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

     public WeatherPopupAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    private LinearLayout addEntry(String label, String content) {
        LinearLayout ll = new LinearLayout(this.context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView l = new TextView(this.context);
        l.setText("label");
        l.setTextColor(Color.BLACK);
        l.setPadding(0,0,20,0);

        TextView c = new TextView(context);
        c.setText("content");
        c.setTextColor(Color.GRAY);

        ll.addView(l);
        ll.addView(c);

        return ll;
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

        TextView title = new TextView(this.context);
        title.setTextColor(Color.BLACK);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(null, Typeface.BOLD);
        title.setText(marker.getTitle());

        TextView snippet = new TextView(this.context);
        snippet.setTextColor(Color.GRAY);
        snippet.setText(marker.getSnippet());

        LatLng latLng = marker.getPosition();

        info.addView(title);
        info.addView(this.addEntry("Label", "Value"));

        info.addView(snippet);

        return info;
    }
}
