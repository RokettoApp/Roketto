package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.AgencyDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.AgencyApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgencyRepository {

    private static final String TAG = "AgencyRepository";
    private final AgencyApiService mAgencyApiService;
    private final AgencyDao mAgencyDao;
    private final MutableLiveData<List<Agency>> mAgencyListLiveData;
    int count;

    public AgencyRepository(Application application) {

        this.mAgencyApiService = ServiceLocator.getInstance().getAgencyApiService();
        mAgencyDao = RokettoDatabase.getDatabase(application).agencyDao();
        mAgencyListLiveData = new MutableLiveData<>();
        count = 0;
    }

    public MutableLiveData<List<Agency>> getAgencyList() {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
//        getAgenciesFromDatabase();
        getNextAgencies(0);
//        fetchAgencies();
        return mAgencyListLiveData;
    }

    public MutableLiveData<List<Agency>> getAgencyById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            Agency agency = mAgencyDao.getById(id);
            if (agency != null) {
                List<Agency> agencyList = new ArrayList<>();
                agencyList.add(agency);
                mAgencyListLiveData.postValue(agencyList);
            } else
                fetchAgencyById(id);
        }).start();
        return mAgencyListLiveData;
    }

    public void getNextAgencies(int lastId) {

        new Thread(() -> {
            List<Agency> agencyList = mAgencyDao.getAllInRange(lastId, lastId + 5);
            if (agencyList.size() == 5) mAgencyListLiveData.postValue(agencyList);
            else fetchAgencies(lastId);
        }).start();
    }

    public void refreshAgencies() {

        fetchAgencies(0);
    }

    private void fetchAgencies(int offset) {

        Call<ResponseList<Agency>> agencyResponseCall =
                mAgencyApiService.getAgencies(5, offset, "id");
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
                    List<Agency> agencyList = new ArrayList<>();
                    Agency agency = response.body();
                    agencyList.add(agency);
                    saveOnDatabase(agencyList);
                    mAgencyListLiveData.postValue(agencyList);
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

        RokettoDatabase.databaseWriteExecutor.execute(() -> mAgencyDao.insertAgencyList(agencyList));
    }

    private void getAgenciesFromDatabase() {

        new Thread(() -> mAgencyListLiveData.postValue(mAgencyDao.getAll())).start();
    }
}
