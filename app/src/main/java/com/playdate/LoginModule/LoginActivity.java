package com.playdate.LoginModule;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.BuildConfig;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.playdate.MainModule.MainActivity;
import com.playdate.R;
import com.playdate.Utils.APIResponseParser;
import com.playdate.Utils.AppConstant;
import com.playdate.Utils.PreferencesManager;
import com.playdate.Utils.ProjectUtilities;
import com.playdate.Utils.WebserviceUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvNormalLogin;
    private ImageView btnFacebookLogin;
    private LoginButton btnFb;
    private CallbackManager callbackManager;
    private String facebook_id, first_name, last_name, email, pic_url, gender;
    private PreferencesManager mPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Initialize facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());

        //set facebook debug mode enable
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        setContentView(R.layout.activity_login);
        mPreferencesManager = PreferencesManager.getInstance();

        //Initialize callback manager object
        callbackManager = CallbackManager.Factory.create();

        initView();
    }

    private void initView() {

        tvNormalLogin = (TextView) findViewById(R.id.tvNormalLogin);
        btnFacebookLogin = (ImageView) findViewById(R.id.btnFacebookLogin);
        btnFb = (LoginButton) findViewById(R.id.btnFb);

        tvNormalLogin.setOnClickListener(this);
        btnFb.setOnClickListener(this);
        btnFacebookLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNormalLogin:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnFacebookLogin:
                btnFb.performClick();
                break;
            case R.id.btnFb:
                facebookLogin();
                break;
            default:
                break;
        }
    }

    //this method set the facebook login permission and register callback
    private void facebookLogin() {
        btnFb.setReadPermissions(Arrays.asList("email", "public_profile"));
        btnFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                //AccessToken Login_By_Facebook_Token = loginResult.getAccessToken().getToken();

                //String facebook_tokan = loginResult.getAccessToken().getToken();


                final GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("LoginActivity", response.toString() + " * " + object.toString());

                                if (response.getError() == null) {
                                    try {
                                        Log.d("status", object.get("email") + "");


                                        facebook_id = object.get("id").toString();
                                        first_name = object.get("first_name").toString();
                                        last_name = object.get("last_name").toString();
                                        pic_url = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                        email = object.get("email").toString();
                                        gender = object.get("gender").toString();
                                        getLoginData();
                                        if (AccessToken.getCurrentAccessToken() != null) {
                                            LoginManager.getInstance().logOut();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location, picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    //This method call webservice data
    private void getLoginData() {

        ProjectUtilities.showProgressDialog(LoginActivity.this);

        JSONObject map = new JSONObject();

        try {
            map.put("facebook_id", facebook_id);
            map.put("first_name", first_name);
            map.put("last_name", last_name);
            map.put("email", email);
            map.put("gender", gender);
            map.put("profile_pic", pic_url);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                WebserviceUrl.FACEBOOK_LOGIN, map,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {

                            System.out.print("Login screen Response:-" + response);

                            try {
                                JSONObject jsonObject = response;
                                if (new APIResponseParser().isSuccess(jsonObject)) {

                                    UserPojo loginPojo = new UserPojo();
                                    loginPojo = new APIResponseParser().getLoginUserDetails(jsonObject.getJSONObject("data").toString());


                                    mPreferencesManager.setBooleanValue(AppConstant.SHARED_PREFERENCE_IS_LOGIN, true);
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_USER_ID, "" + loginPojo.getUser_id());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_FIRST_NAME, loginPojo.getFirst_name());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_LAST_NAME, loginPojo.getLast_name());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_GENDER, loginPojo.getGender());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_FACEBOOK_ID, loginPojo.getFacebook_id());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_PROFILE_PIC, loginPojo.getProfile_pic());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_LAT, loginPojo.getLat());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_LANG, loginPojo.getLng());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_CREATED_AT, loginPojo.getCreated_at());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_NICKNAME, loginPojo.getNickname());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_PHONE, loginPojo.getPhone());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_BIO, "" + loginPojo.getBio());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_DOB, "" + loginPojo.getDob());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_MODIFIED, "" + loginPojo.getModified());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_NOTIFIACATION_ALERT, loginPojo.getShow_notification_alert());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_SHAOW_AGE, loginPojo.getShow_age());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_SHOW_PUBLIC_PROFILE, loginPojo.getShow_public_profile());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_PREFERED_GENDER, loginPojo.getPrefered_gender());
                                    mPreferencesManager.setStringValue(AppConstant.SHARED_PREFERENCE_API_KEY, loginPojo.getApi_key());


                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                    Toast.makeText(LoginActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();


                                    ProjectUtilities.dismissProgressDialog();
                                } else {
                                    ProjectUtilities.dismissProgressDialog();
                                    ProjectUtilities.showAlertDialog(LoginActivity.this, jsonObject.getString("message"));

                                }

                            } catch (JSONException e) {
                                ProjectUtilities.dismissProgressDialog();
                                e.printStackTrace();
                            }
                        }


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
                return headers;
            }

        };
        jsonObjReq.setShouldCache(true);
        // stringRequest.setTag(TAG_REQUEST);
        ProjectUtilities.mVolleyQueue.add(jsonObjReq);

    }


}
