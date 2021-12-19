package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.rokettoapp.roketto.database.AgencyDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.AgencyApiService;
import it.rokettoapp.roketto.util.Constants;
import it.rokettoapp.roketto.util.ServiceLocator;
import it.rokettoapp.roketto.util.SharedPreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgencyRepository {

    private static final String TAG = "AgencyRepository";
    private final AgencyApiService mAgencyApiService;
    private final AgencyDao mAgencyDao;
    private final MutableLiveData<List<Agency>> mAgencyListLiveData;
    private final Application mApplication;
    private final SharedPreferencesProvider mSharedPreferencesProvider;
    int count;

    public AgencyRepository(Application application) {

        this.mAgencyApiService = ServiceLocator.getInstance().getAgencyApiService();
        this.mApplication = application;
        mSharedPreferencesProvider = new SharedPreferencesProvider(mApplication);
        mAgencyDao = RokettoDatabase.getDatabase(application).agencyDao();
        mAgencyListLiveData = new MutableLiveData<>();
        count = 0;
    }

    public MutableLiveData<List<Agency>> getAgencyList() {

        if(mSharedPreferencesProvider.getLastUpdateAgency(Constants.SHARED_PREFERENCES_LAST_UPDATE_AGENCY)==0 ||
           System.currentTimeMillis()- mSharedPreferencesProvider.getLastUpdateAgency(Constants.SHARED_PREFERENCES_LAST_UPDATE_AGENCY) > Constants.HOUR) {
            getAgenciesFromDatabase();
            mSharedPreferencesProvider.setLastUpdateAgency(System.currentTimeMillis(), Constants.SHARED_PREFERENCES_LAST_UPDATE_AGENCY);
        }
//        fetchAgencies();
        return mAgencyListLiveData;
    }

    public void refreshAgencies() {

        fetchAgencies();
    }

    private void fetchAgencies() {

        Call<ResponseList<Agency>> agencyResponseCall = mAgencyApiService.getAgencies(5, count);
        agencyResponseCall.enqueue(new Callback<ResponseList<Agency>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Agency>> call,
                                   @NonNull Response<ResponseList<Agency>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Agency> agencyList = response.body().getResults();
                    mAgencyListLiveData.postValue(agencyList);
                    saveOnDatabase(agencyList);
                    Log.d(TAG, "Retrieved " + agencyList.size() + " agencies.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Agency>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
        count += 5;
    }

    private void fetchAgencyById(int id) {

        Call<Agency> agencyResponseCall = mAgencyApiService.getAgency(id);
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

    private void saveOnDatabase(List<Agency> agencyList) {

        RokettoDatabase.databaseWriteExecutor.execute(() -> {
            mAgencyDao.deleteAll();
            mAgencyDao.insertAgencyList(agencyList);
        });
    }

    private void getAgenciesFromDatabase() {

        new Thread(() -> mAgencyListLiveData.postValue(mAgencyDao.getAll())).start();
    }
}
