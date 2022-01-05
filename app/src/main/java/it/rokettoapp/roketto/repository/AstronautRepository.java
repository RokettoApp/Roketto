package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.AstronautDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.AstronautApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import it.rokettoapp.roketto.util.SharedPreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AstronautRepository {

    private static final String TAG = "AstronautRepository";
    private final AstronautApiService mAstronautApiService;
    private final AstronautDao mAstronautDao;
    private final DatabaseOperations<Integer, Astronaut> databaseOperations;
    private final MutableLiveData<List<Astronaut>> mAstronautListLiveData;
    private final SharedPreferencesProvider mSharedPreferencesProvider;
    int count;

    public AstronautRepository(Application application) {

        this.mAstronautApiService = ServiceLocator.getInstance().getAstronautApiService();
        mSharedPreferencesProvider = new SharedPreferencesProvider(application);
        mAstronautDao = RokettoDatabase.getDatabase(application).astronautDao();
        databaseOperations = new DatabaseOperations<>(mAstronautDao);
        mAstronautListLiveData = new MutableLiveData<>();
        count = 0;
    }

    public MutableLiveData<List<Astronaut>> getLiveData() {

        return mAstronautListLiveData;
    }

    public void getAstronautList(Boolean isConnected) {
        if(isConnected)
//        if(mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_ASTRONAUT)==0 ||
//                System.currentTimeMillis()- mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_ASTRONAUT) > Constants.HOUR) {
            fetchAstronauts();
//            mSharedPreferencesProvider.setLastUpdate(System.currentTimeMillis(), Constants.SHARED_PREFERENCES_LAST_UPDATE_ASTRONAUT);
//        }
        else
            databaseOperations.getListFromDatabase(mAstronautListLiveData);
    }

    public void getNewAstronautList () {
        fetchNewAstronauts();
    }

    public void getAstronautById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            Astronaut astronaut = mAstronautDao.getById(id);
            if (astronaut != null) {
                List<Astronaut> astronautList = new ArrayList<>();
                astronautList.add(astronaut);
                mAstronautListLiveData.postValue(astronautList);
            } else
                fetchAstronautById(id);
        }).start();
    }

    public void getAstronautsByIds(List <Integer> ids){
        new Thread(() -> {
            List<Astronaut> astronautList = new ArrayList<>();
            for (Integer id : ids) {
                Astronaut astronaut = mAstronautDao.getById(id);
                if (astronaut != null) {
                    astronautList.add(astronaut);
                } else
                    fetchAstronautById(id);
            }
            mAstronautListLiveData.postValue(astronautList);
        }).start();
    }

    public void fetchAstronautByIds(Integer id, List<Astronaut> astronautList){
        Call<Astronaut> astronautResponseCall = mAstronautApiService.getAstronaut(id);
        astronautResponseCall.enqueue(new Callback<Astronaut>() {

            @Override
            public void onResponse(@NonNull Call<Astronaut> call,
                                   @NonNull Response<Astronaut> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Astronaut astronaut = response.body();
                    databaseOperations.saveValue(astronaut);
                    astronautList.add(astronaut);
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Astronaut> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void refreshAstronauts() {

        fetchAstronauts();
    }

    private void fetchAstronauts() {

        Call<ResponseList<Astronaut>> astronautResponseCall =
                mAstronautApiService.getAstronauts(5, count);
        astronautResponseCall.enqueue(new Callback<ResponseList<Astronaut>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Astronaut>> call,
                                   @NonNull Response<ResponseList<Astronaut>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Astronaut> astronautList = response.body().getResults();
                    //databaseOperations.saveList(astronautList);
                    mAstronautListLiveData.postValue(astronautList);
                    Log.d(TAG, "Retrieved " + astronautList.size() + " astronauts.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Astronaut>> call,
                                  @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
        count += 5;
    }

    private void fetchNewAstronauts() {

        Call<ResponseList<Astronaut>> astronautResponseCall =
                mAstronautApiService.getAstronauts(5, count);
        astronautResponseCall.enqueue(new Callback<ResponseList<Astronaut>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Astronaut>> call,
                                   @NonNull Response<ResponseList<Astronaut>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Astronaut> astronautList = response.body().getResults();
                    List<Astronaut> currentAstronautList = mAstronautListLiveData.getValue();
                    //databaseOperations.saveList(astronautList);
                    currentAstronautList.addAll(astronautList);
                    mAstronautListLiveData.postValue(currentAstronautList);
                    Log.d(TAG, "Retrieved " + astronautList.size() + " astronauts.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Astronaut>> call,
                                  @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
        count += 5;
    }

    private void fetchAstronautById(int id) {

        Call<Astronaut> astronautResponseCall = mAstronautApiService.getAstronaut(id);
        astronautResponseCall.enqueue(new Callback<Astronaut>() {

            @Override
            public void onResponse(@NonNull Call<Astronaut> call,
                                   @NonNull Response<Astronaut> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Astronaut astronaut = response.body();
                    databaseOperations.saveValue(astronaut);
                    List<Astronaut> astronautList = new ArrayList<>();
                    astronautList.add(astronaut);
                    mAstronautListLiveData.postValue(astronautList);
                    Log.d(TAG, astronaut.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Astronaut> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
