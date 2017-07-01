package com.playdate.MessageHomeModule;

/**
 * Created by Chetan on 07-01-2017.
 */

public class UserMessageHomePojo {
    String first_name, last_message;

    public UserMessageHomePojo(String first_name, String last_message) {
        this.first_name = first_name;
        this.last_message = last_message;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }
}
