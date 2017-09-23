package com.moldedbits.genesis.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.moldedbits.genesis.BaseApplication;
import com.moldedbits.languagetools.models.CategoryProgress;

import java.util.Locale;

import timber.log.Timber;

/**
 * Created by abhishek
 * on 18/03/15.
 */
public class LocalStorage {

    private final SharedPreferences preferences;

    private static final LocalStorage instance = new LocalStorage();

    public static LocalStorage getInstance() {
        return instance;
    }

    private static final String PREFS_NAME = "com.moldedbits.genesis.SharedPrefs";

    private LocalStorage() {
        preferences = BaseApplication.getInstance().getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
    }

    public void storeCategoryProgress(CategoryProgress progress) {
        Gson gson = new Gson();
        Timber.d("Storing progress: %s", gson.toJson(progress));
        preferences.edit().putString(progress.getCategoryKey(), gson.toJson(progress)).apply();
    }

    public String getCategoryProgressString(String categoryKey) {
        if (!preferences.contains(categoryKey)) {
            return "Not started";
        }

        CategoryProgress progress = getCategoryProgress(categoryKey);

        int totalCount = progress.getCompletedPassages().size();
        int completedCount = 0;

        for (Boolean bool : progress.getCompletedPassages()) {
            if (bool) {
                completedCount++;
            }
        }

        if (completedCount == 0) {
            return "Not started";
        } else if (completedCount == totalCount) {
            return "Completed";
        } else {
            return String.format(Locale.US, "Completed %d of %d", completedCount, totalCount);
        }
    }

    public CategoryProgress getCategoryProgress(String categoryKey) {
        if (!preferences.contains(categoryKey)) {
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(preferences.getString(categoryKey, null), CategoryProgress.class);
    }

    public void storeData(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void storeData(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void storeData(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void storeData(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void storeData(String key, float value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return preferences.getString(key, null);
    }

    public float getFloat(String key) {
        return preferences.getFloat(key, 0.0f);
    }

    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }
}
