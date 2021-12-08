package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Location;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.LocationApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {

    private static final String TAG = "LocationRepository";
    private final LocationApiService mLocationApiService;

    public LocationRepository() {

        this.mLocationApiService = ServiceLocator.getInstance().getLocationApiService();
    }

    public void fetchLocations() {

        Call<ResponseList<Location>> locationResponseCall = mLocationApiService.getLocations(5);
        locationResponseCall.enqueue(new Callback<ResponseList<Location>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Location>> call,
                                   @NonNull Response<ResponseList<Location>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Location> locationList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Location location : locationList) {
                        debugString.append(location.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Location>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchLocationById(int id) {

        Call<Location> locationResponseCall = mLocationApiService.getLocation(id);
        locationResponseCall.enqueue(new Callback<Location>() {

            @Override
            public void onResponse(@NonNull Call<Location> call,
                                   @NonNull Response<Location> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Location location = response.body();
                    Log.d(TAG, location.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Location> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
