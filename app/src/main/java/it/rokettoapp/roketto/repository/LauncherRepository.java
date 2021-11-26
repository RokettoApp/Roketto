package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Launcher;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.LauncherApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LauncherRepository {

    private static final String TAG = "LauncherRepository";
    private final LauncherApiService launcherApiService;

    public LauncherRepository() {

        this.launcherApiService = ServiceLocator.getsInstance().getLauncherApiService();
    }

    public void fetchLaunchers() {

        Call<ResponseList<Launcher>> launcherResponseCall = launcherApiService.getLaunchers(5);
        launcherResponseCall.enqueue(new Callback<ResponseList<Launcher>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Launcher>> call,
                                   @NonNull Response<ResponseList<Launcher>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Launcher> launcherList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Launcher launcher : launcherList) {
                        debugString.append(launcher.getSerialNumber()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Launcher>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchLauncherById(int id) {

        Call<Launcher> launcherResponseCall = launcherApiService.getLauncher(id);
        launcherResponseCall.enqueue(new Callback<Launcher>() {

            @Override
            public void onResponse(@NonNull Call<Launcher> call,
                                   @NonNull Response<Launcher> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Launcher launcher = response.body();
                    Log.d(TAG, launcher.getSerialNumber());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Launcher> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
