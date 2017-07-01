package com.playdate.MessageHomeModule;

/**
 * Created by Chetan on 07-01-2017.
 */

public class ConversationPojo {
    String user_id, first_name, last_name, date, message;

    public ConversationPojo() {
    }

    public ConversationPojo(String user_id, String first_name, String last_name, String date, String message) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date = date;
        this.message = message;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
