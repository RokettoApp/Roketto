package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.LaunchDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.LaunchApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchRepository {

    private static final String TAG = "LaunchRepository";
    private final LaunchApiService mLaunchApiService;
    private final LaunchDao mLaunchDao;
    private final DatabaseOperations<String, Launch> databaseOperations;
    private final MutableLiveData<ResponseList<Launch>> mLaunchListLiveData;

    public LaunchRepository(Application application) {

        this.mLaunchApiService = ServiceLocator.getInstance().getLaunchApiService();
        mLaunchDao = RokettoDatabase.getDatabase(application).launchDao();
        databaseOperations = new DatabaseOperations<>(mLaunchDao);
        mLaunchListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ResponseList<Launch>> getLiveData() {

        return mLaunchListLiveData;
    }

    public void getLaunchList(Boolean isConnected) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        databaseOperations.getListFromDatabase(mLaunchListLiveData);
//        fetchLaunches();
    }

    public void getLaunchById(String id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            Launch launch = mLaunchDao.getById(id);
            if (launch != null) {
                ResponseList<Launch> responseList = new ResponseList<>();
                List<Launch> launchList = new ArrayList<>();
                launchList.add(launch);
                Log.d(TAG, launch.getName());
                responseList.setResults(launchList);
                mLaunchListLiveData.postValue(responseList);
            } else
                fetchLaunchById(id);
        }).start();
    }

    public void getLaunchesByIds(List<String> ids){
        new Thread(() -> {
            ResponseList<Launch> responseList = new ResponseList<>();
            List<Launch> launchList = new ArrayList<>();
            for (String id : ids) {
                Launch launch = mLaunchDao.getById(id);
                if (launch != null) {

                    launchList.add(launch);
                    Log.d(TAG, launch.getName());

                } else
                    fetchLaunchById(id);
            }
            responseList.setResults(launchList);
            mLaunchListLiveData.postValue(responseList);
        }).start();
    }

    public void clearLaunches() {

        databaseOperations.deleteAll();
    }

    public void fetchLaunches() {

        Call<ResponseList<Launch>> launchResponseCall = mLaunchApiService.getLaunches(5);
        launchResponseCall.enqueue(new Callback<ResponseList<Launch>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Launch>> call,
                                   @NonNull Response<ResponseList<Launch>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Launch> responseList = new ResponseList<>();
                    List<Launch> launchList = response.body().getResults();
                    databaseOperations.saveList(launchList);
                    responseList.setResults(launchList);
                    mLaunchListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + launchList.size() + " launches.");
                } else {
                    ResponseList<Launch> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mLaunchListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Launch>> call, @NonNull Throwable t) {

                ResponseList<Launch> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mLaunchListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchLaunchById(String id) {

        Call<Launch> launchResponseCall = mLaunchApiService.getLaunch(id);
        launchResponseCall.enqueue(new Callback<Launch>() {

            @Override
            public void onResponse(@NonNull Call<Launch> call,
                                   @NonNull Response<Launch> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Launch> responseList = new ResponseList<>();
                    Launch launch = response.body();
                    databaseOperations.saveValue(launch);
                    List<Launch> launchList = new ArrayList<>();
                    launchList.add(launch);
                    responseList.setResults(launchList);
                    mLaunchListLiveData.postValue(responseList);
                    Log.d(TAG, launch.getName() + " API");
                } else {
                    ResponseList<Launch> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mLaunchListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Launch> call, @NonNull Throwable t) {

                ResponseList<Launch> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mLaunchListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchPreviousLaunches() {

        Call<ResponseList<Launch>> launchResponseCall = mLaunchApiService.getPreviousLaunches(5);
        launchResponseCall.enqueue(new Callback<ResponseList<Launch>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Launch>> call,
                                   @NonNull Response<ResponseList<Launch>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Launch> responseList = new ResponseList<>();
                    List<Launch> launchList = response.body().getResults();
                    databaseOperations.saveList(launchList);
                    responseList.setResults(launchList);
                    mLaunchListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + launchList.size() + " launches.");
                } else {
                    ResponseList<Launch> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mLaunchListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Launch>> call, @NonNull Throwable t) {

                ResponseList<Launch> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mLaunchListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchPreviousLaunchById(String id) {

        Call<Launch> launchResponseCall = mLaunchApiService.getPreviousLaunch(id);
        launchResponseCall.enqueue(new Callback<Launch>() {

            @Override
            public void onResponse(@NonNull Call<Launch> call,
                                   @NonNull Response<Launch> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Launch> responseList = new ResponseList<>();
                    Launch launch = response.body();
                    databaseOperations.saveValue(launch);
                    List<Launch> launchList = new ArrayList<>();
                    launchList.add(launch);
                    responseList.setResults(launchList);
                    mLaunchListLiveData.postValue(responseList);
                    Log.d(TAG, launch.getName());
                } else {
                    ResponseList<Launch> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mLaunchListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Launch> call, @NonNull Throwable t) {

                ResponseList<Launch> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mLaunchListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }
    public void fetchUpcomingLaunches() {

        Call<ResponseList<Launch>> launchResponseCall = mLaunchApiService.getUpcomingLaunches(5);
        launchResponseCall.enqueue(new Callback<ResponseList<Launch>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Launch>> call,
                                   @NonNull Response<ResponseList<Launch>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Launch> responseList = new ResponseList<>();
                    List<Launch> launchList = response.body().getResults();
                    databaseOperations.saveList(launchList);
                    responseList.setResults(launchList);
                    mLaunchListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + launchList.size() + " launches.");
                } else {
                    ResponseList<Launch> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mLaunchListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Launch>> call, @NonNull Throwable t) {

                ResponseList<Launch> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mLaunchListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchUpcomingLaunchById(String id) {

        Call<Launch> launchResponseCall = mLaunchApiService.getUpcomingLaunch(id);
        launchResponseCall.enqueue(new Callback<Launch>() {

            @Override
            public void onResponse(@NonNull Call<Launch> call,
                                   @NonNull Response<Launch> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Launch> responseList = new ResponseList<>();
                    Launch launch = response.body();
                    databaseOperations.saveValue(launch);
                    List<Launch> launchList = new ArrayList<>();
                    launchList.add(launch);
                    responseList.setResults(launchList);
                    mLaunchListLiveData.postValue(responseList);
                    Log.d(TAG, launch.getName());
                } else {
                    ResponseList<Launch> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mLaunchListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Launch> call, @NonNull Throwable t) {

                ResponseList<Launch> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mLaunchListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
