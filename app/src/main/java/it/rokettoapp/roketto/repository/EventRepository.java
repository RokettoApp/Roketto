package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.EventDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.EventApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import it.rokettoapp.roketto.util.SharedPreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private static final String TAG = "EventRepository";
    private final EventApiService mEventApiService;
    private final EventDao mEventDao;
    private final DatabaseOperations<Integer, Event> databaseOperations;
    private final MutableLiveData<ResponseList<Event>> mEventListLiveData;
    private final SharedPreferencesProvider mSharedPreferencesProvider;
    int count;

    public EventRepository(Application application) {

        this.mEventApiService = ServiceLocator.getInstance().getEventApiService();
        mSharedPreferencesProvider = new SharedPreferencesProvider(application);
        mEventDao = RokettoDatabase.getDatabase(application).eventDao();
        databaseOperations = new DatabaseOperations<>(mEventDao);
        mEventListLiveData = new MutableLiveData<>();
        count = 0;
    }

    public MutableLiveData<ResponseList<Event>> getLiveData() {

        return mEventListLiveData;
    }

    public void getEventList(Boolean isConnected) {
        if (isConnected)
//        if(mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_EVENT)==0 ||
//                System.currentTimeMillis()- mSharedPreferencesProvider.getLastUpdate(Constants.SHARED_PREFERENCES_LAST_UPDATE_EVENT) > Constants.HOUR) {
            fetchEvents();
//            mSharedPreferencesProvider.setLastUpdate(System.currentTimeMillis(), Constants.SHARED_PREFERENCES_LAST_UPDATE_EVENT);
//        }
        else
            databaseOperations.getListFromDatabase(mEventListLiveData);
    }

    public void getEventById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            Event event = mEventDao.getById(id);
            if (event != null) {
                ResponseList<Event> responseList = new ResponseList<>();
                List<Event> eventList = new ArrayList<>();
                eventList.add(event);
                responseList.setResults(eventList);
                mEventListLiveData.postValue(responseList);
            } else
                fetchEventById(id);
        }).start();
    }

    public void refreshEvents() {

        new Thread(() -> {
            mEventDao.deleteAll();
            count = 0;
            fetchEvents();
        }).start();
    }

    public void clearEvents() {

        databaseOperations.deleteAll();
    }


    private void fetchEvents() {

        Call<ResponseList<Event>> eventResponseCall = mEventApiService.getEvents(4, 0);
        eventResponseCall.enqueue(new Callback<ResponseList<Event>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Event>> call,
                                   @NonNull Response<ResponseList<Event>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Event> responseList = new ResponseList<>();
                    List<Event> eventList = response.body().getResults();
//                    databaseOperations.saveList(eventList);
                    responseList.setResults(eventList);
                    mEventListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + eventList.size() + " events.");
                } else {
                    ResponseList<Event> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mEventListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Event>> call, @NonNull Throwable t) {

                ResponseList<Event> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mEventListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
        count = 4;
    }

    public void fetchNewEvents(){
        Call<ResponseList<Event>> eventResponseCall = mEventApiService.getEvents(4, count);
        eventResponseCall.enqueue(new Callback<ResponseList<Event>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Event>> call,
                                   @NonNull Response<ResponseList<Event>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Event> responseList = new ResponseList<>();
                    List<Event> eventList = response.body().getResults();
//                    databaseOperations.saveList(eventList);
                    if (mEventListLiveData.getValue() != null) {
                        List<Event> mCurrentEventList = mEventListLiveData.getValue().getResults();
                        mCurrentEventList.addAll(eventList);
                        responseList.setResults(mCurrentEventList);
                    }
                    mEventListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + eventList.size() + " current events.");
                } else {
                    ResponseList<Event> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mEventListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Event>> call, @NonNull Throwable t) {

                ResponseList<Event> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mEventListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
        count += 4;
    }

    private void fetchEventById(int id) {

        Call<Event> eventResponseCall = mEventApiService.getEvent(id);
        eventResponseCall.enqueue(new Callback<Event>() {

            @Override
            public void onResponse(@NonNull Call<Event> call,
                                   @NonNull Response<Event> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Event> responseList = new ResponseList<>();
                    Event event = response.body();
                    databaseOperations.saveValue(event);
                    List<Event> eventList = new ArrayList<>();
                    eventList.add(event);
                    responseList.setResults(eventList);
                    mEventListLiveData.postValue(responseList);
                    Log.d(TAG, event.getName());
                } else {
                    ResponseList<Event> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mEventListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {

                ResponseList<Event> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mEventListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchPreviousEvents() {

        Call<ResponseList<Event>> eventResponseCall = mEventApiService.getPreviousEvents(5);
        eventResponseCall.enqueue(new Callback<ResponseList<Event>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Event>> call,
                                   @NonNull Response<ResponseList<Event>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Event> responseList = new ResponseList<>();
                    List<Event> eventList = response.body().getResults();
                    databaseOperations.saveList(eventList);
                    responseList.setResults(eventList);
                    mEventListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + eventList.size() + " events.");
                } else {
                    ResponseList<Event> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mEventListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Event>> call, @NonNull Throwable t) {

                ResponseList<Event> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mEventListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchPreviousEventById(int id) {

        Call<Event> eventResponseCall = mEventApiService.getPreviousEvent(id);
        eventResponseCall.enqueue(new Callback<Event>() {

            @Override
            public void onResponse(@NonNull Call<Event> call,
                                   @NonNull Response<Event> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Event> responseList = new ResponseList<>();
                    Event event = response.body();
                    databaseOperations.saveValue(event);
                    List<Event> eventList = new ArrayList<>();
                    eventList.add(event);
                    responseList.setResults(eventList);
                    mEventListLiveData.postValue(responseList);
                    Log.d(TAG, event.getName());
                } else {
                    ResponseList<Event> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mEventListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {

                ResponseList<Event> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mEventListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchUpcomingEvents() {

        Call<ResponseList<Event>> eventResponseCall = mEventApiService.getUpcomingEvents(5);
        eventResponseCall.enqueue(new Callback<ResponseList<Event>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Event>> call,
                                   @NonNull Response<ResponseList<Event>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Event> responseList = new ResponseList<>();
                    List<Event> eventList = response.body().getResults();
                    databaseOperations.saveList(eventList);
                    responseList.setResults(eventList);
                    mEventListLiveData.postValue(responseList);
                    Log.d(TAG, "Retrieved " + eventList.size() + " events.");
                } else {
                    ResponseList<Event> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mEventListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Event>> call, @NonNull Throwable t) {

                ResponseList<Event> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mEventListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchUpcomingEventById(int id) {

        Call<Event> eventResponseCall = mEventApiService.getUpcomingEvent(id);
        eventResponseCall.enqueue(new Callback<Event>() {

            @Override
            public void onResponse(@NonNull Call<Event> call,
                                   @NonNull Response<Event> response) {

                if (response.body() != null && response.isSuccessful()) {
                    ResponseList<Event> responseList = new ResponseList<>();
                    Event event = response.body();
                    databaseOperations.saveValue(event);
                    List<Event> eventList = new ArrayList<>();
                    eventList.add(event);
                    responseList.setResults(eventList);
                    mEventListLiveData.postValue(responseList);
                    Log.d(TAG, event.getName());
                } else {
                    ResponseList<Event> errorResponse = new ResponseList<>();
                    errorResponse.setError(true);
                    errorResponse.setMessage(response.message());
                    mEventListLiveData.postValue(errorResponse);
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {

                ResponseList<Event> errorResponse = new ResponseList<>();
                errorResponse.setError(true);
                errorResponse.setMessage(t.getMessage());
                mEventListLiveData.postValue(errorResponse);
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
