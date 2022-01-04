package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.database.SpacecraftDao;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.service.SpacecraftApiService;
import it.rokettoapp.roketto.util.Constants;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import it.rokettoapp.roketto.util.SharedPreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpacecraftRepository {

    private static final String TAG = "SpacecraftRepository";
    private final SpacecraftApiService mSpacecraftApiService;
    private final SpacecraftDao mSpacecraftDao;
    private final DatabaseOperations<Integer, Spacecraft> databaseOperations;
    private final MutableLiveData<ResponseList<Spacecraft>> mSpacecraftListLiveData;
    private final SharedPreferencesProvider mSharedPreferencesProvider;
    int count;

    public SpacecraftRepository(Application application) {

        this.mSpacecraftApiService = ServiceLocator.getInstance().getSpacecraftApiService();
        mSharedPreferencesProvider = new SharedPreferencesProvider(application);
        mSpacecraftDao = RokettoDatabase.getDatabase(application).spacecraftDao();
        databaseOperations = new DatabaseOperations<>(mSpacecraftDao);
        mSpacecraftListLiveData = new MutableLiveData<>();
        count = 0;
    }

    public MutableLiveData<ResponseList<Spacecraft>> getLiveData() {

        return mSpacecraftListLiveData;
    }

    public void getSpacecraftList(Boolean isConnected) {

        if(mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_SPACECRAFT)==0 ||
                System.currentTimeMillis()- mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_SPACECRAFT) > Constants.HOUR &&
            isConnected) {
            fetchSpacecrafts();
            mSharedPreferencesProvider.setLastUpdate(System.currentTimeMillis(), Constants.SHARED_PREFERENCES_LAST_UPDATE_SPACECRAFT);
        }
        else
            databaseOperations.getListFromDatabase(mSpacecraftListLiveData);
    }

    public void getSpacecraftById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            Spacecraft spacecraft = mSpacecraftDao.getById(id);
            if (spacecraft != null) {
                ResponseList<Spacecraft> responseList = new ResponseList<>();
                List<Spacecraft> spacecraftList = new ArrayList<>();
                spacecraftList.add(spacecraft);
                responseList.setResults(spacecraftList);
                mSpacecraftListLiveData.postValue(responseList);
            } else
                fetchSpacecraftById(id);
        }).start();
    }

    public void refreshSpacecrafts() {

        fetchSpacecrafts();
    }

    public void clearSpacecrafts() {

        databaseOperations.deleteAll();
    }

    private void fetchSpacecrafts() {

        Call<ResponseList<Spacecraft>> spacecraftResponseCall =
                mSpacecraftApiService.getSpacecrafts(5, count);
        spacecraftResponseCall.enqueue(new Callback<ResponseList<Spacecraft>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Spacecraft>> call,
                                   @NonNull Response<ResponseList<Spacecraft>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Spacecraft> spacecraftList = response.body().getResults();
                    databaseOperations.saveList(spacecraftList);
                    mSpacecraftListLiveData.postValue(response.body());
                    Log.d(TAG, "Retrieved " + spacecraftList.size() + " spacecrafts.");
                } else {
                    ResponseList<Spacecraft> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mSpacecraftListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Spacecraft>> call,
                                  @NonNull Throwable t) {

                ResponseList<Spacecraft> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mSpacecraftListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
        count += 5;
    }

    private void fetchSpacecraftById(int id) {

        Call<Spacecraft> spacecraftResponseCall = mSpacecraftApiService.getSpacecraft(id);
        spacecraftResponseCall.enqueue(new Callback<Spacecraft>() {

            @Override
            public void onResponse(@NonNull Call<Spacecraft> call,
                                   @NonNull Response<Spacecraft> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Spacecraft> responseList = new ResponseList<>();
                    Spacecraft spacecraft = response.body();
                    databaseOperations.saveValue(spacecraft);
                    List<Spacecraft> spacecraftList = new ArrayList<>();
                    spacecraftList.add(spacecraft);
                    responseList.setResults(spacecraftList);
                    mSpacecraftListLiveData.postValue(responseList);
                    Log.d(TAG, spacecraft.getName());
                } else {
                    ResponseList<Spacecraft> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    Log.e(TAG, "Request failed.");
                    mSpacecraftListLiveData.postValue(errorResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Spacecraft> call, @NonNull Throwable t) {

                ResponseList<Spacecraft> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mSpacecraftListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
