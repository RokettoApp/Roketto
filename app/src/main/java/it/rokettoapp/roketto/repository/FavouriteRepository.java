package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import it.rokettoapp.roketto.database.EventDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.User;
import it.rokettoapp.roketto.util.Constants;
import it.rokettoapp.roketto.util.DatabaseOperations;

public class FavouriteRepository {

    private static final String TAG = "FavouriteRepository";
    private final DatabaseReference mFirebaseDatabase;
    private final MutableLiveData<Boolean> mResponseLiveData;
    private final MutableLiveData<User> mUserLiveData;
    private final MutableLiveData<List<Event>> mEventListLiveData;
    private final EventDao mEventDao;

    public FavouriteRepository(Application application) {

        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL)
                .getReference();
        mResponseLiveData = new MutableLiveData<>();
        mUserLiveData = new MutableLiveData<>();
        mEventListLiveData = new MutableLiveData<>();
        mEventDao = RokettoDatabase.getDatabase(application).eventDao();
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

    public MutableLiveData<List<Event>> getLiveData() {

        return mEventListLiveData;
    }

    public void updateFavouriteEvent(Event event){
        new Thread(() -> {
            mEventDao.update(event);
        }).start();
    }

    public void getFavoritesEvent(){
        new Thread(() -> {
            List<Event> favoritesEvent = mEventDao.getFavorites();
            mEventListLiveData.postValue(favoritesEvent);
        }).start();
    }
}
