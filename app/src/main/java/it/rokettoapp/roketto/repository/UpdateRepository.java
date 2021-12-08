package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Update;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.UpdateApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateRepository {
    
    private static final String TAG = "UpdateRepository";
    private final UpdateApiService mUpdateApiService;

    public UpdateRepository() {

        this.mUpdateApiService = ServiceLocator.getInstance().getUpdateApiService();
    }

    public void fetchUpdates() {

        Call<ResponseList<Update>> updateResponseCall = mUpdateApiService.getUpdates(5);
        updateResponseCall.enqueue(new Callback<ResponseList<Update>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Update>> call,
                                   @NonNull Response<ResponseList<Update>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Update> updateList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Update update : updateList) {
                        debugString.append(update.getCreatedBy()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Update>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchUpdateById(int id) {

        Call<Update> updateResponseCall = mUpdateApiService.getUpdate(id);
        updateResponseCall.enqueue(new Callback<Update>() {

            @Override
            public void onResponse(@NonNull Call<Update> call,
                                   @NonNull Response<Update> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Update update = response.body();
                    Log.d(TAG, update.getCreatedBy());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Update> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
