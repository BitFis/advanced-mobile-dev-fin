package com.example.ex2;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ex2.ui.LabelTextFragment;
import com.example.ex2.ui.WeatherPopupAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap map;

    private CheckBox zoomControlsCheckbox;

    private Marker selectedMarker;

    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);
    private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);

    private static final BiMap<Integer, Integer> RADIOBOX_MAP_TYPES_BIND = HashBiMap.create(new HashMap<Integer, Integer>(){{
        put(R.id.radio_maptype_normal, GoogleMap.MAP_TYPE_NORMAL);
        put(R.id.radio_maptype_terrain, GoogleMap.MAP_TYPE_TERRAIN);
        put(R.id.radio_maptype_hybrid, GoogleMap.MAP_TYPE_HYBRID);
    }});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        this.configureUiSettings(this.map.getUiSettings());

        this.map.setMapType(googleMap.MAP_TYPE_NORMAL);
        this.getRadiobutton(googleMap.MAP_TYPE_NORMAL).setChecked(true);

        this.map.setOnMarkerClickListener(this);
        this.map.setOnMapClickListener(this);

        this.addMarkersToMap();

        this.map.setInfoWindowAdapter(new WeatherPopupAdapter(this.getApplicationContext()));

        this.map.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));
    }

    public void onClick(View v) {
        if(v.getId() == R.id.zoom_checkbox) {
            this.map.getUiSettings().setZoomControlsEnabled(((CheckBox)v).isChecked());
        }
    }

    private void addMarkersToMap() {
        this.map.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane")
                .snippet("Population: 2,074,200"));

        this.map.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .title("Sydney")
                .snippet("Population: 4,627,300"));

        this.map.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .title("Melbourne")
                .snippet("Population: 4,137,400"));

        this.map.addMarker(new MarkerOptions()
                .position(PERTH)
                .title("Perth")
                .snippet("Population: 1,738,800"));

        this.map.addMarker(new MarkerOptions()
                .position(ADELAIDE)
                .title("Adelaide")
                .snippet("Population: 1,213,000"));
    }

    private RadioButton getRadiobutton(int mapType) {
        return (RadioButton)this.findViewById(RADIOBOX_MAP_TYPES_BIND.inverse().get(mapType));
    }

    private int getMapType(int radiobutton) {
        return RADIOBOX_MAP_TYPES_BIND.get(radiobutton);
    }

    public void onMapTypeRadiobuttonChange(View v) {
        this.map.setMapType(this.getMapType(v.getId()));
    }

    public void configureUiSettings(UiSettings settings) {
        settings.setZoomControlsEnabled(((CheckBox)this.findViewById(R.id.zoom_checkbox)).isChecked());
    }

    @Override
    public void onMapClick(LatLng latLng) {
        selectedMarker = null;
    }

    public void selectMarker(Marker marker) {
        this.selectedMarker = marker;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(this.selectedMarker)) {
            selectedMarker = null;
            return true;
        }

        selectedMarker = marker;

        return false;
    }
}
