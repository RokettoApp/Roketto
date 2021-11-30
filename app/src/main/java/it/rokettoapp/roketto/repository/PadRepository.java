package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Pad;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.PadApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PadRepository {

    private static final String TAG = "PadRepository";
    private final PadApiService padApiService;

    public PadRepository() {

        this.padApiService = ServiceLocator.getInstance().getPadApiService();
    }

    public void fetchPads() {

        Call<ResponseList<Pad>> padResponseCall = padApiService.getPads(5);
        padResponseCall.enqueue(new Callback<ResponseList<Pad>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Pad>> call,
                                   @NonNull Response<ResponseList<Pad>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Pad> padList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Pad pad : padList) {
                        debugString.append(pad.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Pad>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchPadById(int id) {

        Call<Pad> padResponseCall = padApiService.getPad(id);
        padResponseCall.enqueue(new Callback<Pad>() {

            @Override
            public void onResponse(@NonNull Call<Pad> call,
                                   @NonNull Response<Pad> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Pad pad = response.body();
                    Log.d(TAG, pad.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Pad> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
