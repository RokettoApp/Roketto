package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.repository.SpacecraftRepository;

public class SpacecraftViewModel extends AndroidViewModel {

    private final SpacecraftRepository mSpacecraftRepository;
    private MutableLiveData<ResponseList<Spacecraft>> mSpacecraftListLivedata;

    public SpacecraftViewModel(@NonNull Application application) {

        super(application);
        mSpacecraftRepository = new SpacecraftRepository(application);
        mSpacecraftListLivedata = mSpacecraftRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<Spacecraft>> getLiveData() {

        return mSpacecraftListLivedata;
    }

    public void getSpacecrafts(Boolean isConnected) {

        mSpacecraftRepository.getSpacecraftList(isConnected);
    }

    public void getSpacecraftById(int id) {

        mSpacecraftRepository.getSpacecraftById(id);
    }

    public void refreshSpacecrafts() {

        mSpacecraftRepository.refreshSpacecrafts();
    }
}
