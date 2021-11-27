package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.service.SpaceStationApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpaceStationRepository {

    private static final String TAG = "SpaceStationRepository";
    private final SpaceStationApiService spaceStationApiService;

    public SpaceStationRepository() {

        this.spaceStationApiService = ServiceLocator.getsInstance().getSpaceStationApiService();
    }

    public void fetchSpaceStations() {

        Call<ResponseList<SpaceStation>> spaceStationResponseCall = spaceStationApiService.getSpaceStations(5);
        spaceStationResponseCall.enqueue(new Callback<ResponseList<SpaceStation>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<SpaceStation>> call,
                                   @NonNull Response<ResponseList<SpaceStation>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<SpaceStation> spaceStationList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (SpaceStation spaceStation : spaceStationList) {
                        debugString.append(spaceStation.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<SpaceStation>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchSpaceStationById(int id) {

        Call<SpaceStation> spaceStationResponseCall = spaceStationApiService.getSpaceStation(id);
        spaceStationResponseCall.enqueue(new Callback<SpaceStation>() {

            @Override
            public void onResponse(@NonNull Call<SpaceStation> call,
                                   @NonNull Response<SpaceStation> response) {

                if (response.body() != null && response.isSuccessful()) {
                    SpaceStation spaceStation = response.body();
                    Log.d(TAG, spaceStation.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SpaceStation> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
