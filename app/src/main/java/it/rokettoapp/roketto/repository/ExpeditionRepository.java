package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.ExpeditionDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.ExpeditionApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpeditionRepository {

    private static final String TAG = "ExpeditionRepository";
    private final ExpeditionApiService mExpeditionApiService;
    private final ExpeditionDao mExpeditionDao;
    private final DatabaseOperations<Integer, Expedition> databaseOperations;
    private final MutableLiveData<List<Expedition>> mExpeditionListLiveData;

    public ExpeditionRepository(Application application) {

        this.mExpeditionApiService = ServiceLocator.getInstance().getExpeditionApiService();
        this.mExpeditionDao = RokettoDatabase.getDatabase(application).expeditionDao();
        databaseOperations = new DatabaseOperations<>(mExpeditionDao);
        this.mExpeditionListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Expedition>> getLiveData() {

        return mExpeditionListLiveData;
    }

    public void getExpeditionList() {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        databaseOperations.getListFromDatabase(mExpeditionListLiveData);
//        fetchExpeditions();
    }

    public void getExpeditionById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            Expedition expedition = mExpeditionDao.getById(id);
            if (expedition != null) {
                List<Expedition> expeditionList = new ArrayList<>();
                expeditionList.add(expedition);
                mExpeditionListLiveData.postValue(expeditionList);
            } else
                fetchExpeditionById(id);
        }).start();
    }

    private void fetchExpeditions() {

        Call<ResponseList<Expedition>> expeditionResponseCall =
                mExpeditionApiService.getExpeditions(5);
        expeditionResponseCall.enqueue(new Callback<ResponseList<Expedition>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Expedition>> call,
                                   @NonNull Response<ResponseList<Expedition>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Expedition> expeditionList = response.body().getResults();
                    databaseOperations.saveList(expeditionList);
                    mExpeditionListLiveData.postValue(expeditionList);
                    Log.d(TAG, "Retrieved " + expeditionList.size() + " expeditions.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Expedition>> call,
                                  @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchExpeditionById(int id) {

        Call<Expedition> expeditionResponseCall = mExpeditionApiService.getExpedition(id);
        expeditionResponseCall.enqueue(new Callback<Expedition>() {

            @Override
            public void onResponse(@NonNull Call<Expedition> call,
                                   @NonNull Response<Expedition> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Expedition expedition = response.body();
                    databaseOperations.saveValue(expedition);
                    List<Expedition> expeditionList = new ArrayList<>();
                    expeditionList.add(expedition);
                    mExpeditionListLiveData.postValue(expeditionList);
                    Log.d(TAG, expedition.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Expedition> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
