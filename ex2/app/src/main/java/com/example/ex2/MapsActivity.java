package com.example.ex2;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

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

import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    private CheckBox zoomControlsCheckbox;

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng rovaniemi = new LatLng(66.503944, 25.729391);

        this.map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        this.map.addMarker(new MarkerOptions().position(rovaniemi).title("Here I am"));
        this.map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onClick(View v) {
        if(v.getId() == R.id.zoom_checkbox) {
            this.map.getUiSettings().setZoomControlsEnabled(((CheckBox)v).isChecked());
        }
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
}
