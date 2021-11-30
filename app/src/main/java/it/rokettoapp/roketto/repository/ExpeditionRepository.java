package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.ExpeditionApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpeditionRepository {

    private static final String TAG = "ExpeditionRepository";
    private final ExpeditionApiService expeditionApiService;

    public ExpeditionRepository() {

        this.expeditionApiService = ServiceLocator.getInstance().getExpeditionApiService();
    }

    public void fetchExpeditions() {

        Call<ResponseList<Expedition>> agencyResponseCall = expeditionApiService.getExpeditions(5);
        agencyResponseCall.enqueue(new Callback<ResponseList<Expedition>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Expedition>> call,
                                   @NonNull Response<ResponseList<Expedition>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Expedition> expeditionList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Expedition expedition : expeditionList) {
                        debugString.append(expedition.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Expedition>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchExpeditionById(int id) {

        Call<Expedition> expeditionResponseCall = expeditionApiService.getExpedition(id);
        expeditionResponseCall.enqueue(new Callback<Expedition>() {

            @Override
            public void onResponse(@NonNull Call<Expedition> call,
                                   @NonNull Response<Expedition> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Expedition expedition = response.body();
                    Log.d(TAG, expedition.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Expedition> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
