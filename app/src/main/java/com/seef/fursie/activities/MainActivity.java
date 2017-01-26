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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.seef.fursie.Position;
import com.seef.fursie.R;

import org.aaronhe.rxgooglemapsbinding.RxGoogleMaps;

import rx.Observable;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
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
        RxGoogleMaps.mapReady(mapFragment).flatMap(new Func1<GoogleMap, Observable<CameraPosition>>() {

            @Override
            public Observable<CameraPosition> call(GoogleMap googleMap) {
                return RxGoogleMaps.cameraPositionChanges(googleMap);
            }

        })
        .flatMap(new Func1<MarkerOptions, Observable<MarkerOptions>>() {
            @Override
            public Observable<MarkerOptions> call(MarkerOptions cameraPosition) {
                return new MarkerOptions().position(new LatLng(44,44));
            }
        });
    }

    public void setLocation(Location location) {
        if (location.getLongitude() != 0.0 && location.getLatitude() != 0.0) {
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

}
