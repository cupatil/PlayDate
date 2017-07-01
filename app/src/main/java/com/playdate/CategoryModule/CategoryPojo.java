package com.playdate.CategoryModule;

import com.playdate.HomeModule.UserPojo;

import java.util.ArrayList;

/**
 * Created by Chetan on 21-01-2017.
 */

public class CategoryPojo {
    String category_id;
    String category_name;
    boolean favourite;
    String color;

    ArrayList<UserPojo> userPojoArrayList;


    public CategoryPojo() {
    }

    public CategoryPojo(String category_id, String category_name, boolean favourite, String color, ArrayList<UserPojo> userPojoArrayList) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.favourite = favourite;
        this.color = color;
        this.userPojoArrayList = userPojoArrayList;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<UserPojo> getUserPojoArrayList() {
        return userPojoArrayList;
    }

    public void setUserPojoArrayList(ArrayList<UserPojo> userPojoArrayList) {
        this.userPojoArrayList = userPojoArrayList;
    }
}
