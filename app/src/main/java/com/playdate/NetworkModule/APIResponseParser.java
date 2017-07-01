package com.playdate.NetworkModule;

/**
 * Created by admin on 1/18/2016.
 */


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class APIResponseParser {

    public boolean isSuccess(JSONObject jsonObject) {

        if (jsonObject != null) {
            try {

                if (jsonObject.getString("error").equals("false")) {
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }




   /* public List<CategotyPojo> getVideoCategory(String jsonString) {

        List<CategotyPojo> videoList = new ArrayList<>();

        Gson gson = new Gson();
        videoList = Arrays.asList(gson.fromJson(jsonString, CategotyPojo[].class));

        return videoList;
    }

    public List<VideoPojo> getVideo(String jsonString) {

        List<VideoPojo> videoList = new ArrayList<>();

        Gson gson = new Gson();
        videoList = Arrays.asList(gson.fromJson(jsonString, VideoPojo[].class));

        return videoList;
    }

    public List<CountryPojo> getCountyList(String jsonString) {

        List<CountryPojo> countryList = new ArrayList<>();

        Gson gson = new Gson();
        countryList = Arrays.asList(gson.fromJson(jsonString, CountryPojo[].class));

        return countryList;
    }

    public List<StatePojo> getStateList(String jsonString) {

        List<StatePojo> stateList = new ArrayList<>();

        Gson gson = new Gson();
        stateList = Arrays.asList(gson.fromJson(jsonString, StatePojo[].class));

        return stateList;
    }

    public List<CityPojo> getCityList(String jsonString) {

        List<CityPojo> cityList = new ArrayList<>();

        Gson gson = new Gson();
        cityList = Arrays.asList(gson.fromJson(jsonString, CityPojo[].class));

        return cityList;
    }

    public List<FaqPojo> getFAQSList(String jsonString) {

        List<FaqPojo> faqPojoArrayList = new ArrayList<>();

        Gson gson = new Gson();
        faqPojoArrayList = Arrays.asList(gson.fromJson(jsonString, FaqPojo[].class));

        return faqPojoArrayList;
    }

    public List<PaymentPojo> getPayment(String jsonString) {

        List<PaymentPojo> paymentPojoArrayList = new ArrayList<>();

        Gson gson = new Gson();
        paymentPojoArrayList = Arrays.asList(gson.fromJson(jsonString, PaymentPojo[].class));

        return paymentPojoArrayList;
    }

    public List<ReportSpamOptionPojo> getReportAsSpamOption(String jsonString) {

        List<ReportSpamOptionPojo> reportSpamOptionPojoArrayList = new ArrayList<>();

        Gson gson = new Gson();
        reportSpamOptionPojoArrayList = Arrays.asList(gson.fromJson(jsonString, ReportSpamOptionPojo[].class));

        return reportSpamOptionPojoArrayList;
    }

    public List<VideoCommentPojo> getVideoComment(String jsonString) {

        List<VideoCommentPojo> videoCommentPojoArrayList = new ArrayList<>();

        Gson gson = new Gson();
        videoCommentPojoArrayList = Arrays.asList(gson.fromJson(jsonString, VideoCommentPojo[].class));

        return videoCommentPojoArrayList;
    }

    public UserPojo getLoginUserDetails(String jsonString) {

        UserPojo loginPojo = new UserPojo();

        Gson gson = new Gson();
        loginPojo = gson.fromJson(jsonString, UserPojo.class);

        return loginPojo;
    }
*/
}
