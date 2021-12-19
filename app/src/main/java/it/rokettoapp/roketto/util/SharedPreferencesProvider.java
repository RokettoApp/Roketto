package it.rokettoapp.roketto.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesProvider {

    private final Application mApplication;

    public SharedPreferencesProvider(Application application) {
        this.mApplication = application;
    }
    public long getLastUpdate(String type) {
        SharedPreferences sharedPref =
                mApplication.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getLong(type, 0);
    }

    public void setLastUpdate(long lastUpdate, String type) {
        SharedPreferences sharedPref = mApplication.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(type, lastUpdate);
        editor.apply();
    }


}