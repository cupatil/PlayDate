package com.playdate.LoginModule;

/**
 * Created by admin on 11/21/2016.
 */

public class UserPojo {


    private String user_id, first_name, last_name, gender, email, facebook_id, profile_pic, lat,
            lng, created_at, nickname, phone, country_name, city_name, bio,
            dob, modified, show_notification_alert, show_age, show_public_profile, prefered_gender, api_key;

    public UserPojo() {
    }

    public UserPojo(String user_id, String first_name, String last_name, String gender, String email, String facebook_id, String profile_pic, String lat, String lng, String created_at, String nickname, String phone, String country_name, String city_name, String bio, String dob, String modified, String show_notification_alert, String show_age, String show_public_profile, String prefered_gender, String api_key) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.facebook_id = facebook_id;
        this.profile_pic = profile_pic;
        this.lat = lat;
        this.lng = lng;
        this.created_at = created_at;
        this.nickname = nickname;
        this.phone = phone;
        this.country_name = country_name;
        this.city_name = city_name;
        this.bio = bio;
        this.dob = dob;
        this.modified = modified;
        this.show_notification_alert = show_notification_alert;
        this.show_age = show_age;
        this.show_public_profile = show_public_profile;
        this.prefered_gender = prefered_gender;
        this.api_key = api_key;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getShow_notification_alert() {
        return show_notification_alert;
    }

    public void setShow_notification_alert(String show_notification_alert) {
        this.show_notification_alert = show_notification_alert;
    }

    public String getShow_age() {
        return show_age;
    }

    public void setShow_age(String show_age) {
        this.show_age = show_age;
    }

    public String getShow_public_profile() {
        return show_public_profile;
    }

    public void setShow_public_profile(String show_public_profile) {
        this.show_public_profile = show_public_profile;
    }

    public String getPrefered_gender() {
        return prefered_gender;
    }

    public void setPrefered_gender(String prefered_gender) {
        this.prefered_gender = prefered_gender;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
