package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import it.rokettoapp.roketto.model.User;
import it.rokettoapp.roketto.util.Constants;

public class FavouriteRepository {

    private static final String TAG = "FavouriteRepository";
    private final DatabaseReference mFirebaseDatabase;
    private final MutableLiveData<Boolean> mResponseLiveData;
    private final MutableLiveData<User> mUserLiveData;

    public FavouriteRepository() {

        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL)
                .getReference();
        mResponseLiveData = new MutableLiveData<>();
        mUserLiveData = new MutableLiveData<>();
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
}
