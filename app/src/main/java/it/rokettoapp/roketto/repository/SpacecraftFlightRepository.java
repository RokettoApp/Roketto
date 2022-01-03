package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.database.SpacecraftFlightDao;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.service.SpacecraftFlightApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpacecraftFlightRepository {

    private static final String TAG = "SpacecraftFlightRepo";
    private final SpacecraftFlightApiService mSpacecraftFlightApiService;
    private final SpacecraftFlightDao mSpacecraftFlightDao;
    private final DatabaseOperations<Integer, SpacecraftFlight> databaseOperations;
    private final MutableLiveData<List<SpacecraftFlight>> mSpacecraftFlightListLiveData;
    int count;

    public SpacecraftFlightRepository(Application application) {

        this.mSpacecraftFlightApiService =
                ServiceLocator.getInstance().getSpacecraftFlightApiService();
        mSpacecraftFlightDao = RokettoDatabase.getDatabase(application).spacecraftFlightDao();
        databaseOperations = new DatabaseOperations<>(mSpacecraftFlightDao);
        mSpacecraftFlightListLiveData = new MutableLiveData<>();
        count = 0;
    }

    public MutableLiveData<List<SpacecraftFlight>> getLiveData() {

        return mSpacecraftFlightListLiveData;
    }

    public void getSpacecraftFlightList(Boolean isConnected) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        databaseOperations.getListFromDatabase(mSpacecraftFlightListLiveData);
//        fetchSpacecraftFlights();
    }

    public void getSpacecraftFlightById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            SpacecraftFlight spacecraftFlight = mSpacecraftFlightDao.getById(id);
            if (spacecraftFlight != null) {
                List<SpacecraftFlight> spacecraftFlightList = new ArrayList<>();
                spacecraftFlightList.add(spacecraftFlight);
                mSpacecraftFlightListLiveData.postValue(spacecraftFlightList);
            } else
                fetchSpacecraftFlightById(id);
        }).start();
    }

    private void fetchSpacecraftFlights() {

        Call<ResponseList<SpacecraftFlight>> spacecraftResponseCall =
                mSpacecraftFlightApiService.getSpacecraftFlights(5);
        spacecraftResponseCall.enqueue(new Callback<ResponseList<SpacecraftFlight>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<SpacecraftFlight>> call,
                                   @NonNull Response<ResponseList<SpacecraftFlight>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<SpacecraftFlight> spacecraftFlightList = response.body().getResults();
                    mSpacecraftFlightListLiveData.postValue(spacecraftFlightList);
                    databaseOperations.saveList(spacecraftFlightList);
                    Log.d(TAG, "Retrieved " + spacecraftFlightList.size()
                            + " spacecraft flights.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<SpacecraftFlight>> call,
                                  @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchSpacecraftFlightById(int id) {

        Call<SpacecraftFlight> spacecraftResponseCall =
                mSpacecraftFlightApiService.getSpacecraftFlight(id);
        spacecraftResponseCall.enqueue(new Callback<SpacecraftFlight>() {

            @Override
            public void onResponse(@NonNull Call<SpacecraftFlight> call,
                                   @NonNull Response<SpacecraftFlight> response) {

                if (response.body() != null && response.isSuccessful()) {
                    SpacecraftFlight spacecraftFlight = response.body();
                    databaseOperations.saveValue(spacecraftFlight);
                    List<SpacecraftFlight> spacecraftFlightList = new ArrayList<>();
                    spacecraftFlightList.add(spacecraftFlight);
                    mSpacecraftFlightListLiveData.postValue(spacecraftFlightList);
                    Log.d(TAG, spacecraftFlight.getDestination());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SpacecraftFlight> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}