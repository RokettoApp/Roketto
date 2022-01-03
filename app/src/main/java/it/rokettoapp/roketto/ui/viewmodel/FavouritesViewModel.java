package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.User;
import it.rokettoapp.roketto.repository.FavouriteRepository;

public class FavouritesViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> mResponseLiveData;
    private MutableLiveData<User> mUserLiveData;
    private final FavouriteRepository mFavouriteRepository;

    public FavouritesViewModel(@NonNull Application application) {

        super(application);
        mFavouriteRepository = new FavouriteRepository();
    }

    public MutableLiveData<Boolean> saveFavouriteEvents(User user) {

        mResponseLiveData = mFavouriteRepository.saveUserFavouriteEvents(user);
        return mResponseLiveData;
    }

    public MutableLiveData<User> readFavouriteEvents(String id) {

        mUserLiveData = mFavouriteRepository.readUserInfo(id);
        return mUserLiveData;
    }
}
