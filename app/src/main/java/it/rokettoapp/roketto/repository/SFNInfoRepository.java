package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.SFNInfo;
import it.rokettoapp.roketto.service.SFNInfoApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SFNInfoRepository {

    private static final String TAG = "SFNInfoRepository";
    private final SFNInfoApiService mSfnInfoApiService;

    public SFNInfoRepository() {

        this.mSfnInfoApiService = ServiceLocator.getInstance().getSFNInfoApiService();
    }

    public void fetchInfo() {

        Call<SFNInfo> sfnInfoResponseCall = mSfnInfoApiService.getInfo();
        sfnInfoResponseCall.enqueue(new Callback<SFNInfo>() {

            @Override
            public void onResponse(@NonNull Call<SFNInfo> call,
                                   @NonNull Response<SFNInfo> response) {

                if (response.body() != null && response.isSuccessful()) {
                    SFNInfo sfnInfo = response.body();
                    Log.d(TAG, sfnInfo.getApiVersion());
                } else {
                    ResponseList<SFNInfo> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SFNInfo> call, @NonNull Throwable t) {

                ResponseList<SFNInfo> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
