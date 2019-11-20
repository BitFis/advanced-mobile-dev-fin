package com.example.ex2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.List;

public class OpenStreetMapActivity extends AppCompatActivity implements LocationListener {
    private final static String TAG = "OpenStreetMapActivity";

    private MapView map = null;

    private MyLocationNewOverlay mLocationOverlay;
    private CompassOverlay mCompassOverlay;
    private ScaleBarOverlay mScaleBarOverlay;
    private RotationGestureOverlay mRotationGestureOverlay;
    protected ImageButton btCenterMap;
    protected ImageButton btFollowMe;
    private LocationManager lm;
    private Location currentLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fix issue with sqllite having issue writing/data to sdcard
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_open_street_map);

        this.map = this.findViewById(R.id.map);


        final IMapController mapController = map.getController();
        GeoPoint rovaniemi = new GeoPoint(66.5, 25.7);

        mapController.setZoom(10.5);
        mapController.setCenter(rovaniemi);

        final DisplayMetrics dm = this.getResources().getDisplayMetrics();

        this.mCompassOverlay = new CompassOverlay(this, new InternalCompassOrientationProvider(this), this.map);
        this.mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this), this.map);

        this.mScaleBarOverlay = new ScaleBarOverlay(this.map);
        this.mScaleBarOverlay.setCentred(true);
        this.mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);

        this.map.setTileSource(TileSourceFactory.MAPNIK);

        this.map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        this.map.setMultiTouchControls(true);
        this.map.setTilesScaledToDpi(true);
        this.map.setFlingEnabled(true);

        this.map.getOverlays().add(this.mLocationOverlay);
        this.map.getOverlays().add(this.mCompassOverlay);
        this.map.getOverlays().add(this.mScaleBarOverlay);

        this.mLocationOverlay.enableMyLocation();
        this.mLocationOverlay.enableFollowLocation();
        this.mLocationOverlay.setOptionsMenuEnabled(true);
        this.mCompassOverlay.enableCompass();

        btCenterMap = this.findViewById(R.id.ic_center_map);
 
        btCenterMap.setOnClickListener((View v) -> {
            Log.i(TAG, "centerMap clicked ");
            if (currentLocation != null) {
                GeoPoint myPosition = new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
                OpenStreetMapActivity.this.map.getController().animateTo(myPosition);
            }
        });

        btFollowMe = this.findViewById(R.id.ic_follow_me);

        btFollowMe.setOnClickListener((View v) -> {
            Log.i(TAG, "btFollowMe clicked ");
            if (!mLocationOverlay.isFollowLocationEnabled()) {
                mLocationOverlay.enableFollowLocation();
                btFollowMe.setImageResource(R.drawable.ic_follow_me_on);
            } else {
                mLocationOverlay.disableFollowLocation();
                btFollowMe.setImageResource(R.drawable.ic_follow_me);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        this.map.onResume();

        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try{
            //this fails on AVD 19s, even with the appcompat check, says no provided named gps is available
            this.lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0l,0f,this);
        } catch (Exception ex){}

        try{
            this.lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0l,0f,this);
        }catch (Exception ex){}

        this.mLocationOverlay.enableFollowLocation();
        this.mLocationOverlay.enableMyLocation();
        this.mScaleBarOverlay.disableScaleBar();
    }

    @Override
    public void onPause() {
        super.onPause();

        try{
            this.lm.removeUpdates(this);
        }catch (Exception ex){}

        this.mCompassOverlay.disableCompass();
        this.mLocationOverlay.disableFollowLocation();
        this.mLocationOverlay.disableMyLocation();
        this.mScaleBarOverlay.enableScaleBar();

        map.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.lm=null;
        this.currentLocation=null;

        this.mLocationOverlay=null;
        this.mCompassOverlay=null;
        this.mScaleBarOverlay=null;
        this.mRotationGestureOverlay=null;
        this.btCenterMap=null;
        this.btFollowMe=null;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.currentLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
