package com.playdate.GameModule;

/**
 * Created by Chetan on 23-01-2017.
 */

public class GamePojo {

    String user_id, user_name, remaining_time, percentage, streak, game_status;

    public GamePojo() {
    }

    public GamePojo(String user_id, String user_name, String remaining_time, String percentage, String streak, String game_status) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.remaining_time = remaining_time;
        this.percentage = percentage;
        this.streak = streak;
        this.game_status = game_status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRemaining_time() {
        return remaining_time;
    }

    public void setRemaining_time(String remaining_time) {
        this.remaining_time = remaining_time;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getStreak() {
        return streak;
    }

    public void setStreak(String streak) {
        this.streak = streak;
    }

    public String getGame_status() {
        return game_status;
    }

    public void setGame_status(String game_status) {
        this.game_status = game_status;
    }
}
