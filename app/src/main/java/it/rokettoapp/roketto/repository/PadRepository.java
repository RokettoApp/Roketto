package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.PadDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Pad;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.PadApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PadRepository {

    private static final String TAG = "PadRepository";
    private final PadApiService mPadApiService;
    private final PadDao padDao;
    private final DatabaseOperations<Integer, Pad> databaseOperations;
    private final MutableLiveData<List<Pad>> mPadListLiveData;

    public PadRepository(Application application) {

        this.mPadApiService = ServiceLocator.getInstance().getPadApiService();
        padDao = RokettoDatabase.getDatabase(application).padDao();
        databaseOperations = new DatabaseOperations<>(padDao);
        mPadListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Pad>> getLiveData() {

        return mPadListLiveData;
    }

    public void getPadList(Boolean isConnected) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        databaseOperations.getListFromDatabase(mPadListLiveData);
//        fetchPads();
    }

    public void getPadById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            Pad pad = padDao.getById(id);
            if (pad != null) {
                List<Pad> padList = new ArrayList<>();
                padList.add(pad);
                mPadListLiveData.postValue(padList);
            } else
                fetchPadById(id);
        }).start();
    }

    public void clearPads() {

        databaseOperations.deleteAll();
    }

    private void fetchPads() {

        Call<ResponseList<Pad>> padResponseCall = mPadApiService.getPads(5);
        padResponseCall.enqueue(new Callback<ResponseList<Pad>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Pad>> call,
                                   @NonNull Response<ResponseList<Pad>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Pad> padList = response.body().getResults();
                    databaseOperations.saveList(padList);
                    mPadListLiveData.postValue(padList);
                    Log.d(TAG, "Retrieved " + padList.size() + " pads.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Pad>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchPadById(int id) {

        Call<Pad> padResponseCall = mPadApiService.getPad(id);
        padResponseCall.enqueue(new Callback<Pad>() {

            @Override
            public void onResponse(@NonNull Call<Pad> call,
                                   @NonNull Response<Pad> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Pad pad = response.body();
                    databaseOperations.saveValue(pad);
                    List<Pad> padList = new ArrayList<>();
                    padList.add(pad);
                    mPadListLiveData.postValue(padList);
                    Log.d(TAG, pad.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Pad> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
