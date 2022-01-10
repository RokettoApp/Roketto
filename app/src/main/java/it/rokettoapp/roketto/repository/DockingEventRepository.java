package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.DockingEventDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.DockingEvent;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.DockingEventApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DockingEventRepository {

    private static final String TAG = "DockingEventRepository";
    private final DockingEventApiService mEockingEventApiService;
    private final DockingEventDao mDockingEventDao;
    private final DatabaseOperations<Integer, DockingEvent> databaseOperations;
    private final MutableLiveData<ResponseList<DockingEvent>> mDockingEventListLiveData;

    public DockingEventRepository(Application application) {

        this.mEockingEventApiService = ServiceLocator.getInstance().getDockingEventApiService();
        mDockingEventDao = RokettoDatabase.getDatabase(application).dockingEventDao();
        databaseOperations = new DatabaseOperations<>(mDockingEventDao);
        mDockingEventListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ResponseList<DockingEvent>> getLiveData() {

        return mDockingEventListLiveData;
    }

    public void getDockingEventList(Boolean isConnected) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        databaseOperations.getListFromDatabase(mDockingEventListLiveData);
//        fetchDockingEvents();
    }

    public void getDockingEventById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            DockingEvent dockingEvent = mDockingEventDao.getById(id);
            if (dockingEvent != null) {
                ResponseList<DockingEvent> responseList = new ResponseList<>();
                List<DockingEvent> dockingEventList = new ArrayList<>();
                dockingEventList.add(dockingEvent);
                responseList.setResults(dockingEventList);
                mDockingEventListLiveData.postValue(responseList);
            } else
                fetchDockingEventById(id);
        }).start();
    }

    public void clearDockingEvents() {

        databaseOperations.deleteAll();
    }

    private void fetchDockingEvents() {

        Call<ResponseList<DockingEvent>> astronautResponseCall =
                mEockingEventApiService.getDockingEvents(5);
        astronautResponseCall.enqueue(new Callback<ResponseList<DockingEvent>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<DockingEvent>> call,
                                   @NonNull Response<ResponseList<DockingEvent>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<DockingEvent> responseList = new ResponseList<>();
                    List<DockingEvent> dockingEventList = response.body().getResults();
                    databaseOperations.saveList(dockingEventList);
                    responseList.setResults(dockingEventList);
                    mDockingEventListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + dockingEventList.size() + " docking events.<");
                } else {
                    ResponseList<DockingEvent> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mDockingEventListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<DockingEvent>> call,
                                  @NonNull Throwable t) {

                ResponseList<DockingEvent> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mDockingEventListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchDockingEventById(int id) {

        Call<DockingEvent> astronautResponseCall = mEockingEventApiService.getDockingEvent(id);
        astronautResponseCall.enqueue(new Callback<DockingEvent>() {

            @Override
            public void onResponse(@NonNull Call<DockingEvent> call,
                                   @NonNull Response<DockingEvent> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<DockingEvent> responseList = new ResponseList<>();
                    DockingEvent dockingEvent = response.body();
                    databaseOperations.saveValue(dockingEvent);
                    List<DockingEvent> dockingEventList = new ArrayList<>();
                    dockingEventList.add(dockingEvent);
                    responseList.setResults(dockingEventList);
                    mDockingEventListLiveData.postValue(responseList);
                    Log.d(TAG, dockingEvent.getDockingLocation().getName());
                } else {
                    ResponseList<DockingEvent> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mDockingEventListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<DockingEvent> call, @NonNull Throwable t) {

                ResponseList<DockingEvent> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mDockingEventListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
