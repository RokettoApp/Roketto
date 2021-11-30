package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.AgencyApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgencyRepository {

    private static final String TAG = "AgencyRepository";
    private final AgencyApiService agencyApiService;

    public AgencyRepository() {

        this.agencyApiService = ServiceLocator.getInstance().getAgencyApiService();
    }

    public void fetchAgencies() {

        Call<ResponseList<Agency>> agencyResponseCall = agencyApiService.getAgencies(5);
        agencyResponseCall.enqueue(new Callback<ResponseList<Agency>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Agency>> call,
                                   @NonNull Response<ResponseList<Agency>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Agency> agencyList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Agency agency : agencyList) {
                        debugString.append(agency.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Agency>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchAgencyById(int id) {

        Call<Agency> agencyResponseCall = agencyApiService.getAgency(id);
        agencyResponseCall.enqueue(new Callback<Agency>() {

            @Override
            public void onResponse(@NonNull Call<Agency> call,
                                   @NonNull Response<Agency> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Agency agency = response.body();
                    Log.d(TAG, agency.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Agency> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
