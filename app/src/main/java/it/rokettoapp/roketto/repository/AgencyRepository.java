package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.AgencyList;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.AstronautList;
import it.rokettoapp.roketto.service.AgencyApiService;
import it.rokettoapp.roketto.service.AstronautApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgencyRepository {

    private static final String TAG = "AgencyRepository";
    private final AgencyApiService agencyApiService;

    public AgencyRepository() {

        this.agencyApiService = ServiceLocator.getsInstance().getAgencyApiService();
    }

    public void fetchAgencies() {

        Call<AgencyList> astronautResponseCall = agencyApiService.getAgencies(5);
        astronautResponseCall.enqueue(new Callback<AgencyList>() {

            @Override
            public void onResponse(@NonNull Call<AgencyList> call,
                                   @NonNull Response<AgencyList> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Agency> astronautList = response.body().getAgencies();
                    StringBuilder debugString = new StringBuilder();
                    for (Agency agency : astronautList) {
                        debugString.append(agency.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<AgencyList> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchAgencyById(int id) {

        Call<Agency> astronautResponseCall = agencyApiService.getAgency(id);
        astronautResponseCall.enqueue(new Callback<Agency>() {

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