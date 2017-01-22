package com.seef.fursie.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.arsy.maps_library.MapRadar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.seef.fursie.Position;
import com.seef.fursie.R;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapRadar mapRadar;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configInit();
    }

    private void configInit() {
        configMap();
    }

    private void configMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    public void setLocation(Location location) {
        if (location.getLongitude() != 0.0 && location.getLatitude() != 0.0) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14));
            googleMap.setBuildingsEnabled(true);
            googleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())));
            mapRadar.withLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
            if (!mapRadar.isAnimationRunning()) {
                mapRadar.withDistance(2000);
                mapRadar.startRadarAnimation();
            }
        }
    }

    private void configPosition() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Position position = new Position();
        position.setMainActivity(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) position);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mapRadar.isAnimationRunning())
            mapRadar.stopRadarAnimation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json)
        );
        mapRadar = new MapRadar(this.googleMap, new LatLng(-17.783471, -63.181579), this);
        configPosition();
    }
}
