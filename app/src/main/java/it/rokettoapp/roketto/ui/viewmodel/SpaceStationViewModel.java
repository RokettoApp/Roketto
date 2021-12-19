package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.repository.SpaceStationRepository;

public class SpaceStationViewModel extends AndroidViewModel {

    private final SpaceStationRepository mSpaceStationRepository;
    private final MutableLiveData<List<SpaceStation>> mSpaceStationListLivedata;

    public SpaceStationViewModel(@NonNull Application application) {

        super(application);
        mSpaceStationRepository = new SpaceStationRepository(application);
        mSpaceStationListLivedata = mSpaceStationRepository.getLiveData();
    }

    public MutableLiveData<List<SpaceStation>> getLiveData() {

        return mSpaceStationListLivedata;
    }

    public void getSpaceStations() {

        mSpaceStationRepository.getSpaceStationList();
    }

    public void getSpaceStationById(int id) {

        mSpaceStationRepository.getSpaceStationById(id);
    }
}
