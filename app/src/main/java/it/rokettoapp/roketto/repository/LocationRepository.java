package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.LocationDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Location;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.LocationApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {

    private static final String TAG = "LocationRepository";
    private final LocationApiService mLocationApiService;
    private final LocationDao mLocationDao;
    private final DatabaseOperations<Integer, Location> databaseOperations;
    private final MutableLiveData<ResponseList<Location>> mLocationListLiveData;

    public LocationRepository(Application application) {

        this.mLocationApiService = ServiceLocator.getInstance().getLocationApiService();
        mLocationDao = RokettoDatabase.getDatabase(application).locationDao();
        databaseOperations = new DatabaseOperations<>(mLocationDao);
        mLocationListLiveData = new MutableLiveData<>();
    }
    public MutableLiveData<ResponseList<Location>> getLiveData() {

        return mLocationListLiveData;
    }

    public void getLocationList(Boolean isConnected) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        databaseOperations.getListFromDatabase(mLocationListLiveData);
//        fetchLocations();
    }

    public void getLocationById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            Location location = mLocationDao.getById(id);
            if (location != null) {
                ResponseList<Location> responseList = new ResponseList<>();
                List<Location> locationList = new ArrayList<>();
                locationList.add(location);
                responseList.setResults(locationList);
                mLocationListLiveData.postValue(responseList);
            } else
                fetchLocationById(id);
        }).start();
    }

    public void clearLocations() {

        databaseOperations.deleteAll();
    }

    private void fetchLocations() {

        Call<ResponseList<Location>> locationResponseCall = mLocationApiService.getLocations(5);
        locationResponseCall.enqueue(new Callback<ResponseList<Location>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Location>> call,
                                   @NonNull Response<ResponseList<Location>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Location> locationList = response.body().getResults();
                    databaseOperations.saveList(locationList);
                    mLocationListLiveData.postValue(response.body());
                    Log.d(TAG, "Retrieved " + locationList.size() + " locations.");
                } else {
                    ResponseList<Location> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mLocationListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Location>> call, @NonNull Throwable t) {

                ResponseList<Location> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mLocationListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchLocationById(int id) {

        Call<Location> locationResponseCall = mLocationApiService.getLocation(id);
        locationResponseCall.enqueue(new Callback<Location>() {

            @Override
            public void onResponse(@NonNull Call<Location> call,
                                   @NonNull Response<Location> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Location> responseList = new ResponseList<>();
                    Location location = response.body();
                    databaseOperations.saveValue(location);
                    List<Location> locationList = new ArrayList<>();
                    locationList.add(location);
                    responseList.setResults(locationList);
                    mLocationListLiveData.postValue(responseList);
                    Log.d(TAG, location.getName());
                } else {
                    ResponseList<Location> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mLocationListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Location> call, @NonNull Throwable t) {

                ResponseList<Location> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mLocationListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
