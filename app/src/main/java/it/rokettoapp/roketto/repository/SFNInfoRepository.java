package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import it.rokettoapp.roketto.model.SFNInfo;
import it.rokettoapp.roketto.service.SFNInfoApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SFNInfoRepository {

    private static final String TAG = "SFNInfoRepository";
    private final SFNInfoApiService sfnInfoApiService;

    public SFNInfoRepository() {

        this.sfnInfoApiService = ServiceLocator.getInstance().getSFNInfoApiService();
    }

    public void fetchInfo() {

        Call<SFNInfo> agencyResponseCall = sfnInfoApiService.getInfo();
        agencyResponseCall.enqueue(new Callback<SFNInfo>() {

            @Override
            public void onResponse(@NonNull Call<SFNInfo> call,
                                   @NonNull Response<SFNInfo> response) {

                if (response.body() != null && response.isSuccessful()) {
                    SFNInfo sfnInfo = response.body();
                    Log.d(TAG, sfnInfo.getApiVersion());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SFNInfo> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
