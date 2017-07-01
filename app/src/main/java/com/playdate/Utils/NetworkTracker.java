package com.playdate.Utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;


public class NetworkTracker extends Service implements LocationListener {

    private final Context mContext;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    public boolean canGetLocation;
    public boolean canFromLocation = false;


    public Location networkLocation; // Network Location

    public double networkLatitude; // latitude
    public double networkLongitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager networkLocationManager;

    public NetworkTracker(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {

        try {
            networkLocationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // getting network status

            if (ProjectUtilities.checkInternetAvailable(mContext)) {
                isNetworkEnabled = networkLocationManager
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }

            if (!isNetworkEnabled) {
                this.canGetLocation = false;
            } else {
                this.canGetLocation = true;
                getDataFromNetwork();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return networkLocation;
    }

    private void getDataFromNetwork() {
        this.canFromLocation = true;

        if (networkLocation == null) {
            networkLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            Log.d("Network", "Network");
            if (networkLocationManager != null) {
                networkLocation = networkLocationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (networkLocation != null) {
                    networkLatitude = networkLocation.getLatitude();
                    networkLongitude = networkLocation.getLongitude();
                }
            }
        }
    }

    public void stopUsingGPS() {
        if (networkLocationManager != null) {
            networkLocationManager.removeUpdates(NetworkTracker.this);
        }
    }

    public double getLatitude() {
        if (networkLocation != null) {
            networkLatitude = networkLocation.getLatitude();
        }
        return networkLatitude;
    }

    public double getLongitude() {
        if (networkLocation != null) {
            networkLongitude = networkLocation.getLongitude();

        }
        return networkLongitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public boolean canFromLocation() {
        return this.canGetLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
