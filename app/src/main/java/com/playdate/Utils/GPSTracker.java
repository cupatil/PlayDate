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

public class GPSTracker extends Service implements LocationListener {

    private final Context mContext;

    // flag for GPS status
    public boolean isGPSEnabled = false;

    // flag for GPS status
    public  boolean canGetLocation;
    public  boolean canFromLocation;

    public Location gpsLocation; // Gps Location

    public double gpsLatitude; // latitude
    public double gpsLongitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager gpsLocationManager;

    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {

        try {
            gpsLocationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = gpsLocationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (!isGPSEnabled) {
                this.canGetLocation = false;
            } else {
                this.canGetLocation = true;
                getDataFromGPS();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gpsLocation;
    }

    private void getDataFromGPS() {
        // TODO Auto-generated method stub
        this.canFromLocation = false;
        if (gpsLocation == null) {

            gpsLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            Log.d("GPS Enabled", "GPS Enabled");
            if (gpsLocationManager != null) {
                gpsLocation = gpsLocationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (gpsLocation != null) {
                    gpsLatitude = gpsLocation.getLatitude();
                    gpsLongitude = gpsLocation.getLongitude();
                }
            }

        }
    }

    public void stopUsingGPS() {
        if (gpsLocationManager != null) {
            gpsLocationManager.removeUpdates(GPSTracker.this);
        }
    }

    public double getLatitude() {
        if (gpsLocation != null) {
            gpsLatitude = gpsLocation.getLatitude();
        }
        return gpsLatitude;
    }

    public double getLongitude() {
        if (gpsLocation != null) {
            gpsLongitude = gpsLocation.getLongitude();
        }
        return gpsLongitude;
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
