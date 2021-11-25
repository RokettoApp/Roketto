package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.service.SpacecraftApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpacecraftRepository {

    private static final String TAG = "SpacecraftRepository";
    private final SpacecraftApiService spacecraftApiService;

    public SpacecraftRepository() {

        this.spacecraftApiService = ServiceLocator.getsInstance().getSpacecraftApiService();
    }

    public void fetchSpacecrafts() {

        Call<ResponseList<Spacecraft>> astronautResponseCall = spacecraftApiService.getSpacecrafts(5);
        astronautResponseCall.enqueue(new Callback<ResponseList<Spacecraft>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Spacecraft>> call,
                                   @NonNull Response<ResponseList<Spacecraft>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Spacecraft> spacecraftList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Spacecraft spacecraft : spacecraftList) {
                        debugString.append(spacecraft.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Spacecraft>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchSpacecraftById(int id) {

        Call<Spacecraft> astronautResponseCall = spacecraftApiService.getSpacecraft(id);
        astronautResponseCall.enqueue(new Callback<Spacecraft>() {

            @Override
            public void onResponse(@NonNull Call<Spacecraft> call,
                                   @NonNull Response<Spacecraft> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Spacecraft spacecraft = response.body();
                    Log.d(TAG, spacecraft.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Spacecraft> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
    public void fetchSpacecraftFlights() {

        Call<ResponseList<SpacecraftFlight>> astronautResponseCall = spacecraftApiService.getSpacecraftFlights(5);
        astronautResponseCall.enqueue(new Callback<ResponseList<SpacecraftFlight>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<SpacecraftFlight>> call,
                                   @NonNull Response<ResponseList<SpacecraftFlight>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<SpacecraftFlight> spacecraftFlightList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (SpacecraftFlight spacecraftFlight : spacecraftFlightList) {
                        debugString.append(spacecraftFlight.getDestination()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<SpacecraftFlight>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchSpacecraftFlightById(int id) {

        Call<SpacecraftFlight> astronautResponseCall = spacecraftApiService.getSpacecraftFlight(id);
        astronautResponseCall.enqueue(new Callback<SpacecraftFlight>() {

            @Override
            public void onResponse(@NonNull Call<SpacecraftFlight> call,
                                   @NonNull Response<SpacecraftFlight> response) {

                if (response.body() != null && response.isSuccessful()) {
                    SpacecraftFlight spacecraftFlight = response.body();
                    Log.d(TAG, spacecraftFlight.getDestination());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SpacecraftFlight> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
