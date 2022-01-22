package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.EventDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.User;
import it.rokettoapp.roketto.service.EventApiService;
import it.rokettoapp.roketto.util.Constants;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteRepository {

    private static final String TAG = "FavouriteRepository";
    private final DatabaseReference mFirebaseDatabase;
    private final MutableLiveData<Boolean> mResponseLiveData;
    private final MutableLiveData<User> mUserLiveData;
    private final MutableLiveData<ResponseList<Event>> mEventListLiveData;
    private final EventDao mEventDao;
    private final EventApiService mEventApiService;
    private final DatabaseOperations<Integer, Event> databaseOperations;

    public FavouriteRepository(Application application) {

        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL)
                .getReference();
        mResponseLiveData = new MutableLiveData<>();
        mUserLiveData = new MutableLiveData<>();
        mEventListLiveData = new MutableLiveData<>();
        mEventDao = RokettoDatabase.getDatabase(application).eventDao();
        mEventApiService = ServiceLocator.getInstance().getEventApiService();
        databaseOperations = new DatabaseOperations<>(mEventDao);
    }

    public MutableLiveData<Boolean> saveUserFavouriteEvents(User user) {

        if (user != null) {
            mFirebaseDatabase.child(Constants.USER_COLLECTION).child(user.getId()).setValue(user)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mResponseLiveData.postValue(true);
                        } else {
                            mResponseLiveData.postValue(false);
                        }
                    });
        }
        return mResponseLiveData;
    }

    public MutableLiveData<User> readUserInfo(String uId) {

        mFirebaseDatabase.child(Constants.USER_COLLECTION).child(uId).get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                Log.d(TAG, String.valueOf(task.getResult().getValue()));
                mUserLiveData.postValue(task.getResult().getValue(User.class));
            }
            else {
                Log.d(TAG, "Error getting data", task.getException());
                mUserLiveData.postValue(null);
            }
        });

        return mUserLiveData;
    }

    public MutableLiveData<ResponseList<Event>> getLiveData() {

        return mEventListLiveData;
    }

    public void updateFavouriteEvent(Event event){

        new Thread(() -> mEventDao.update(event)).start();
    }

    public void getFavoritesEvent(){

        new Thread(() -> {
            ResponseList<Event> responseList = new ResponseList<>();
            List<Event> favoritesEvent = mEventDao.getFavorites();
            responseList.setResults(favoritesEvent);
            mEventListLiveData.postValue(responseList);
        }).start();
    }

    public void refreshFavoriteEvents(List<Integer> idList) {

        new Thread(() -> {
            mEventDao.clearfavourites();
            ResponseList<Event> responseList = new ResponseList<>();
            List<Event> eventList = new ArrayList<>();
            for (Integer id : idList) {
                Event event = mEventDao.getById(id);
                if (event != null) {
                    eventList.add(event);
                    if (event.getFavourite() != 1) {
                        event.setFavourite(1);
                        mEventDao.update(event);
                    }
                } else {
                    fetchEventById(id);
                }
            }
            responseList.setResults(eventList);
            mEventListLiveData.postValue(responseList);
            Log.d(TAG, "Refreshed favourite events.");
        }).start();
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
                    event.setFavourite(1);
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
