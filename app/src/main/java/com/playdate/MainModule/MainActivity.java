package com.playdate.MainModule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.playdate.CategoryModule.CategoryFragment;
import com.playdate.HomeModule.HomeFragment;
import com.playdate.MessageHomeModule.UserMessageListFragment;
import com.playdate.ProfileModule.ProfileFragment;
import com.playdate.R;
import com.playdate.SettingModule.SettingFragment;
import com.playdate.TempFragment;
import com.playdate.Utils.AppConstant;
import com.playdate.Utils.GPSTracker;
import com.playdate.Utils.NetworkTracker;
import com.playdate.Utils.PreferencesManager;
import com.playdate.Utils.ProjectUtilities;
import com.playdate.Utils.WebserviceUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Toolbar toolbar;
    private ImageView ivHome, ivMessage, ivGame, ivCategory, ivProfile;
    private Button btnHome, btnMessage, btnGame, btnCategory, btnProfile;
    public static LinearLayout linearLayoutBottom;
    public static ImageButton btnSetting, btnEdit, btnBack;
    public static ImageView ivStart, ivUserProfileIcon;
    public static RelativeLayout rlProfile;

    protected static final String TAG = "MainActivity";
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;

    LocationRequest locationRequest;
    // GPSTracker class
    GPSTracker gpsTracker;

    // NetworkTracker class
    NetworkTracker networkTracker;

    //double latitude, longitude;
    private Timer timerTask;

    PreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageLoader();
        sharedPreferencesManager = PreferencesManager.getInstance();
        initView();

        setFragment(new HomeFragment());
        ivHome.setImageResource(R.drawable.ico_cards_ro);

        buildGoogleApiClient();
    }

    private void initView() {
        // Initialize and set toolbar as an actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnSetting = (ImageButton) toolbar.findViewById(R.id.btnSetting);
        btnEdit = (ImageButton) toolbar.findViewById(R.id.btnEdit);
        btnBack = (ImageButton) toolbar.findViewById(R.id.btnBack);
        ivStart = (ImageView) toolbar.findViewById(R.id.ivStart);
        ivUserProfileIcon = (ImageView) toolbar.findViewById(R.id.ivUserProfileIcon);
        rlProfile = (RelativeLayout) toolbar.findViewById(R.id.rlProfile);
        toolbar.setTitle("");
        //Set up toolbar
        setSupportActionBar(toolbar);

        //Initializr other controls
        linearLayoutBottom = (LinearLayout) findViewById(R.id.linearLayoutBottom);
        ivHome = (ImageView) findViewById(R.id.ivHome);
        ivMessage = (ImageView) findViewById(R.id.ivMessage);
        ivGame = (ImageView) findViewById(R.id.ivGame);
        ivCategory = (ImageView) findViewById(R.id.ivCategory);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);

        btnHome = (Button) findViewById(R.id.btnHome);
        btnMessage = (Button) findViewById(R.id.btnMessage);
        btnGame = (Button) findViewById(R.id.btnGame);
        btnCategory = (Button) findViewById(R.id.btnCategory);
        btnProfile = (Button) findViewById(R.id.btnProfile);


        // Set on click listener to required controls
        ivHome.setOnClickListener(this);
        ivMessage.setOnClickListener(this);
        ivGame.setOnClickListener(this);
        ivCategory.setOnClickListener(this);
        ivProfile.setOnClickListener(this);

        btnHome.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnGame.setOnClickListener(this);
        btnCategory.setOnClickListener(this);
        btnProfile.setOnClickListener(this);

        btnSetting.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnHome:
                linearLayoutBottom.setVisibility(View.VISIBLE);
                replaceFragment(new HomeFragment());

                ivHome.setImageResource(R.drawable.ico_cards_ro);
                ivMessage.setImageResource(R.drawable.ico_message);
                ivGame.setImageResource(R.drawable.icon_heart);
                ivCategory.setImageResource(R.drawable.icon_setp);
                ivProfile.setImageResource(R.drawable.icon_user);
                break;
            case R.id.btnMessage:
                linearLayoutBottom.setVisibility(View.VISIBLE);
                replaceFragment(new UserMessageListFragment());

                ivHome.setImageResource(R.drawable.ico_cards);
                ivMessage.setImageResource(R.drawable.ico_message_ro);
                ivGame.setImageResource(R.drawable.icon_heart);
                ivCategory.setImageResource(R.drawable.icon_setp);
                ivProfile.setImageResource(R.drawable.icon_user);
                break;
            case R.id.btnGame:
                linearLayoutBottom.setVisibility(View.VISIBLE);
                replaceFragment(new TempFragment());
                //replaceFragment(new GameHomeFragment());

                ivHome.setImageResource(R.drawable.ico_cards);
                ivMessage.setImageResource(R.drawable.ico_message);
                ivGame.setImageResource(R.drawable.icon_heart_ro);
                ivCategory.setImageResource(R.drawable.icon_setp);
                ivProfile.setImageResource(R.drawable.icon_user);
                break;
            case R.id.btnCategory:
                linearLayoutBottom.setVisibility(View.VISIBLE);
                //replaceFragment(new TempFragment());
                replaceFragment(new CategoryFragment());

                ivHome.setImageResource(R.drawable.ico_cards);
                ivMessage.setImageResource(R.drawable.ico_message);
                ivGame.setImageResource(R.drawable.icon_heart);
                ivCategory.setImageResource(R.drawable.icon_setp_ro);
                ivProfile.setImageResource(R.drawable.icon_user);
                break;
            case R.id.btnProfile:
                linearLayoutBottom.setVisibility(View.VISIBLE);
                replaceFragment(new ProfileFragment());

                ivHome.setImageResource(R.drawable.ico_cards);
                ivMessage.setImageResource(R.drawable.ico_message);
                ivGame.setImageResource(R.drawable.icon_heart);
                ivCategory.setImageResource(R.drawable.icon_setp);
                ivProfile.setImageResource(R.drawable.icon_user_ro);
                break;

            case R.id.btnSetting:
                linearLayoutBottom.setVisibility(View.GONE);
                replaceFragmentWithBackStack(MainActivity.this, new SettingFragment());
                break;
            case R.id.btnEdit:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void setFragment(Fragment fragment) {
        // Initialize fragment transaction object
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_framelayout, fragment);
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        // Initialize fragment transaction object
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_framelayout, fragment);
        fragmentTransaction.commit();

    }

    public static void replaceFragmentWithBackStack(Context mContext, Fragment fragment) {
        // Initialize fragment transaction object
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_framelayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .memoryCacheExtraOptions(480, 800)
                // default = device screen dimensions
                .threadPoolSize(3)
                // default
                .threadPriority(Thread.NORM_PRIORITY - 1)
                // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
                .discCacheSize(50 * 1024 * 1024) // 缓冲大小
                .discCacheFileCount(100) // 缓冲文件数目
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(this)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs().build();

        // 2.单例ImageLoader类的初始化
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

        } else {
            linearLayoutBottom.setVisibility(View.VISIBLE);
        }
    }

    public static void hideShowToolbarIcon(String action) {

        if (action.equals("conversion")) {
            btnSetting.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
            btnBack.setVisibility(View.VISIBLE);
            rlProfile.setVisibility(View.VISIBLE);
            ivStart.setVisibility(View.GONE);
        } else if (action.equals("profile")) {
            btnSetting.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.GONE);
            rlProfile.setVisibility(View.GONE);
            ivStart.setVisibility(View.VISIBLE);
        } else if (action.equals("back")) {
            btnSetting.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
            btnBack.setVisibility(View.VISIBLE);
            rlProfile.setVisibility(View.GONE);
            ivStart.setVisibility(View.VISIBLE);
        } else {
            btnSetting.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
            btnBack.setVisibility(View.GONE);
            rlProfile.setVisibility(View.GONE);
            ivStart.setVisibility(View.VISIBLE);
        }

    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }

        stopTask();
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        settingsrequest();

    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    public void settingsrequest() {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3 * 1000);
        locationRequest.setFastestInterval(2 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        //startLocationUpdates();
                        startTask();
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:

                        startTask();
                        break;
                    case Activity.RESULT_CANCELED:
                        settingsrequest();//keep asking if imp or do whatever
                        break;
                }
                break;
        }
    }

    private void startTask() {
        if (timerTask == null) {
            timerTask = new Timer();
            timerTask.scheduleAtFixedRate(new getGpsLocationData(), new Date(),
                    5000);// 300000
        }
    }

    private void stopTask() {
        if (timerTask != null) {

            timerTask.cancel();
            gpsTracker.stopUsingGPS();
            networkTracker.stopUsingGPS();
        }
    }

    private void getGpsData() {
        // TODO Auto-generated method stub

        // create GPSTracker class object
        gpsTracker = new GPSTracker(MainActivity.this);

        // create NetworkTracker class object
        networkTracker = new NetworkTracker(MainActivity.this);

        if (!networkTracker.canGetLocation() && !gpsTracker.canGetLocation()) {
            //stopTask();

            //showSettingsAlert();

        } else {
            if (networkTracker.canGetLocation() && !gpsTracker.canGetLocation()) {

                if (networkTracker.networkLocation != null) {
                    changeView(true);
                }
            } else if (gpsTracker.canGetLocation()
                    && !networkTracker.canGetLocation()) {

                if (gpsTracker.gpsLocation != null) {
                    changeView(false);

                }
            } else if (networkTracker.canGetLocation()
                    && gpsTracker.canGetLocation()) {
                if (gpsTracker.gpsLocation == null) {
                    changeView(true);
                    // tvError.setText("Waiting for GPS to track location");

                } else if (gpsTracker.gpsLocation != null) {
                    changeView(false);

                }
            }
        }

    }

    private void changeView(boolean tracker) {

        if (tracker == true) {
            ProjectUtilities.latitude = networkTracker.getLatitude();
            ProjectUtilities.longitude = networkTracker.getLongitude();


        }
        if (tracker == false) {
            ProjectUtilities.latitude = gpsTracker.getLatitude();
            ProjectUtilities.longitude = gpsTracker.getLongitude();

        }

        if (ProjectUtilities.latitude != 0 && ProjectUtilities.longitude != 0) {
            updateLatitudeLongitude();
        }
        //Toast.makeText(MainActivity.this, "Lat: " + latitude + "\nLog: " + longitude, Toast.LENGTH_SHORT).show();


    }

    class getGpsLocationData extends TimerTask {

        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub

                    getGpsData();
                }
            });

        }
    }

    //This method call webservice for update latitude and longitude
    private void updateLatitudeLongitude() {


        JSONObject map = new JSONObject();

        try {
            map.put("lat", "" + ProjectUtilities.latitude);
            map.put("lng", "" + ProjectUtilities.longitude);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String url = WebserviceUrl.UPDATE_LATITUDE_LONGITUDE + sharedPreferencesManager.getStringValue(AppConstant.SHARED_PREFERENCE_USER_ID);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                url, map,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        System.out.println("Location update webservice responce: " + response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("X-Authorization", sharedPreferencesManager.getStringValue(AppConstant.SHARED_PREFERENCE_API_KEY));
                return headers;
            }

        };
        jsonObjReq.setShouldCache(true);
        // stringRequest.setTag(TAG_REQUEST);
        ProjectUtilities.mVolleyQueue.add(jsonObjReq);

    }

}
