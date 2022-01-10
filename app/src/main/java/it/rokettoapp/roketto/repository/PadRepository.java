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
    private final MutableLiveData<ResponseList<Pad>> mPadListLiveData;

    public PadRepository(Application application) {

        this.mPadApiService = ServiceLocator.getInstance().getPadApiService();
        padDao = RokettoDatabase.getDatabase(application).padDao();
        databaseOperations = new DatabaseOperations<>(padDao);
        mPadListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ResponseList<Pad>> getLiveData() {

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
                ResponseList<Pad> responseList = new ResponseList<>();
                List<Pad> padList = new ArrayList<>();
                padList.add(pad);
                responseList.setResults(padList);
                mPadListLiveData.postValue(responseList);
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
                    mPadListLiveData.postValue(response.body());
                    Log.d(TAG, "Retrieved " + padList.size() + " pads.");
                } else {
                    ResponseList<Pad> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mPadListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Pad>> call, @NonNull Throwable t) {

                ResponseList<Pad> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mPadListLiveData.postValue(errorResponse);
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
                    ResponseList<Pad> responseList = new ResponseList<>();
                    Pad pad = response.body();
                    databaseOperations.saveValue(pad);
                    List<Pad> padList = new ArrayList<>();
                    padList.add(pad);
                    responseList.setResults(padList);
                    mPadListLiveData.postValue(responseList);
                    Log.d(TAG, pad.getName());
                } else {
                    ResponseList<Pad> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mPadListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Pad> call, @NonNull Throwable t) {

                ResponseList<Pad> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mPadListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
