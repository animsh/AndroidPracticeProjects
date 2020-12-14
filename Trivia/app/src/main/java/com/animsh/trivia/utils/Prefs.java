package com.animsh.trivia.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Activity activity) {
        this.preferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public void saveHighPoints(int score) {
        int lastScore = preferences.getInt("high_score", 0);
        if (score > lastScore) {
            preferences.edit().putInt("high_score", score).apply();
        }
    }

    public int getHighPoints() {
        return preferences.getInt("high_score", 0);
    }

    public void saveState(int index) {
        preferences.edit().putInt("index", index).apply();
    }

    public int getState() {
        return preferences.getInt("index", 0);
    }

    public void saveCurrentScore(int score) {
        preferences.edit().putInt("score", score).apply();
    }

    public int getCurrentScore() {
        return preferences.getInt("score", 0);
    }
}
