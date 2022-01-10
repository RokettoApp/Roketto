package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.repository.SpaceStationRepository;

public class SpaceStationViewModel extends AndroidViewModel {

    private final SpaceStationRepository mSpaceStationRepository;
    private final MutableLiveData<ResponseList<SpaceStation>> mSpaceStationListLivedata;

    public SpaceStationViewModel(@NonNull Application application) {

        super(application);
        mSpaceStationRepository = new SpaceStationRepository(application);
        mSpaceStationListLivedata = mSpaceStationRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<SpaceStation>> getLiveData() {

        return mSpaceStationListLivedata;
    }

    public void getSpaceStations(Boolean isConnected) {

        mSpaceStationRepository.getSpaceStationList(isConnected);
    }

    public void getSpaceStationById(int id) {

        mSpaceStationRepository.getSpaceStationById(id);
    }
}
