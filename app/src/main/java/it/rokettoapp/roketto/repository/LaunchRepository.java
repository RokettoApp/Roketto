package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.LaunchApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchRepository {

    private static final String TAG = "LaunchRepository";
    private final LaunchApiService launchApiService;

    public LaunchRepository() {

        this.launchApiService = ServiceLocator.getsInstance().getLaunchApiService();
    }

    public void fetchLaunches() {

        Call<ResponseList<Launch>> launchResponseCall = launchApiService.getLaunches(5);
        launchResponseCall.enqueue(new Callback<ResponseList<Launch>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Launch>> call,
                                   @NonNull Response<ResponseList<Launch>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Launch> launchList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Launch launch : launchList) {
                        debugString.append(launch.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Launch>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchLaunchById(String id) {

        Call<Launch> launchResponseCall = launchApiService.getLaunch(id);
        launchResponseCall.enqueue(new Callback<Launch>() {

            @Override
            public void onResponse(@NonNull Call<Launch> call,
                                   @NonNull Response<Launch> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Launch launch = response.body();
                    Log.d(TAG, launch.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Launch> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchPreviousLaunches() {

        Call<ResponseList<Launch>> launchResponseCall = launchApiService.getPreviousLaunches(5);
        launchResponseCall.enqueue(new Callback<ResponseList<Launch>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Launch>> call,
                                   @NonNull Response<ResponseList<Launch>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Launch> launchList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Launch launch : launchList) {
                        debugString.append(launch.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Launch>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchPreviousLaunchById(String id) {

        Call<Launch> launchResponseCall = launchApiService.getPreviousLaunch(id);
        launchResponseCall.enqueue(new Callback<Launch>() {

            @Override
            public void onResponse(@NonNull Call<Launch> call,
                                   @NonNull Response<Launch> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Launch launch = response.body();
                    Log.d(TAG, launch.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Launch> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
    public void fetchUpcomingLaunches() {

        Call<ResponseList<Launch>> launchResponseCall = launchApiService.getUpcomingLaunches(5);
        launchResponseCall.enqueue(new Callback<ResponseList<Launch>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Launch>> call,
                                   @NonNull Response<ResponseList<Launch>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Launch> launchList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Launch launch : launchList) {
                        debugString.append(launch.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Launch>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchUpcomingLaunchById(String id) {

        Call<Launch> launchResponseCall = launchApiService.getUpcomingLaunch(id);
        launchResponseCall.enqueue(new Callback<Launch>() {

            @Override
            public void onResponse(@NonNull Call<Launch> call,
                                   @NonNull Response<Launch> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Launch launch = response.body();
                    Log.d(TAG, launch.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Launch> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
