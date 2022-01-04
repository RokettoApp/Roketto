package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.database.SpaceStationDao;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.service.SpaceStationApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpaceStationRepository {

    private static final String TAG = "SpaceStationRepository";
    private final SpaceStationApiService mSpaceStationApiService;
    private final SpaceStationDao mSpaceStationDao;
    private final DatabaseOperations<Integer, SpaceStation> databaseOperations;
    private final MutableLiveData<List<SpaceStation>> mSpaceStationListLiveData;

    public SpaceStationRepository(Application application) {

        this.mSpaceStationApiService = ServiceLocator.getInstance().getSpaceStationApiService();
        mSpaceStationDao = RokettoDatabase.getDatabase(application).spaceStationDao();
        databaseOperations = new DatabaseOperations<>(mSpaceStationDao);
        mSpaceStationListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<SpaceStation>> getLiveData() {

        return mSpaceStationListLiveData;
    }

    public void getSpaceStationList(Boolean isConnected) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        databaseOperations.getListFromDatabase(mSpaceStationListLiveData);
//        fetchSpaceStations();
    }

    public void getSpaceStationById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            SpaceStation spaceStation = mSpaceStationDao.getById(id);
            if (spaceStation != null) {
                List<SpaceStation> spaceStationList = new ArrayList<>();
                spaceStationList.add(spaceStation);
                mSpaceStationListLiveData.postValue(spaceStationList);
            } else
                fetchSpaceStationById(id);
        }).start();
    }

    public void clearSpaceStations() {

        databaseOperations.deleteAll();
    }

    private void fetchSpaceStations() {

        Call<ResponseList<SpaceStation>> spaceStationResponseCall =
                mSpaceStationApiService.getSpaceStations(5);
        spaceStationResponseCall.enqueue(new Callback<ResponseList<SpaceStation>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<SpaceStation>> call,
                                   @NonNull Response<ResponseList<SpaceStation>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<SpaceStation> spaceStationList = response.body().getResults();
                    databaseOperations.saveList(spaceStationList);
                    mSpaceStationListLiveData.postValue(spaceStationList);
                    Log.d(TAG, "Retrieved " + spaceStationList.size() + " space stations.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<SpaceStation>> call,
                                  @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchSpaceStationById(int id) {

        Call<SpaceStation> spaceStationResponseCall = mSpaceStationApiService.getSpaceStation(id);
        spaceStationResponseCall.enqueue(new Callback<SpaceStation>() {

            @Override
            public void onResponse(@NonNull Call<SpaceStation> call,
                                   @NonNull Response<SpaceStation> response) {

                if (response.body() != null && response.isSuccessful()) {
                    SpaceStation spaceStation = response.body();
                    databaseOperations.saveValue(spaceStation);
                    List<SpaceStation> spaceStationList = new ArrayList<>();
                    spaceStationList.add(spaceStation);
                    mSpaceStationListLiveData.postValue(spaceStationList);
                    Log.d(TAG, spaceStation.getName());
                } else {
                    Log.e(TAG, "Request failed: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SpaceStation> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
