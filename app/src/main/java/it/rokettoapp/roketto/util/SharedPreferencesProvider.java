package it.rokettoapp.roketto.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesProvider {

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesProvider(Application application) {

        this.sharedPreferences = application
                .getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public long getLastUpdate(String type) {

        return sharedPreferences.getLong(type, 0);
    }

    public void setLastUpdate(long lastUpdate, String type) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(type, lastUpdate);
        editor.apply();
    }

    public void setAuthenticationToken(String token) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.AUTHENTICATION_TOKEN, token);
        editor.apply();
    }

    public void setUserId(String userId) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.USER_ID, userId);
        editor.apply();
    }

    public void setLoginSkipped() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.SHARED_PREFERENCES_LOGIN_SKIPPED, true);
        editor.apply();
    }

    public boolean isLoginSkipped() {

        return sharedPreferences.getBoolean(Constants.SHARED_PREFERENCES_LOGIN_SKIPPED, false);
    }

    public void deleteAll() {

        sharedPreferences.edit().clear().apply();
    }
}