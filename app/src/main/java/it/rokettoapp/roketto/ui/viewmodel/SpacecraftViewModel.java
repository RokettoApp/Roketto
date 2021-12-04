package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.repository.SpacecraftRepository;

public class SpacecraftViewModel extends AndroidViewModel {

    private final SpacecraftRepository mSpacecraftRepository;
    private MutableLiveData<List<Spacecraft>> mSpacecraftListLivedata;

    public SpacecraftViewModel(@NonNull Application application) {

        super(application);
        mSpacecraftRepository = new SpacecraftRepository(application);
    }

    public MutableLiveData<List<Spacecraft>> getSpacecrafts() {

        if (mSpacecraftListLivedata == null) fetchSpacecrafts();
        return mSpacecraftListLivedata;
    }

    private void fetchSpacecrafts() {

        mSpacecraftListLivedata = mSpacecraftRepository.getSpacecraftList();
    }

    public void refreshSpacecrafts() {

        mSpacecraftRepository.refreshSpacecrafts();
    }
}
