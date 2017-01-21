package com.seef.fursie;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.seef.fursie.activities.MainActivity;

/**
 * Created by jhonsalguero on 1/20/17.
 */

public class Position implements LocationListener {

    MainActivity mainActivity;

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onLocationChanged(Location location) {
        mainActivity.setLocation(location);
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
