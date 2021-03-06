package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.repository.SpacecraftFlightRepository;

public class SpacecraftFlightViewModel extends AndroidViewModel {

    private final SpacecraftFlightRepository mSpacecraftFlightRepository;
    private MutableLiveData<ResponseList<SpacecraftFlight>> mSpacecraftListLivedata;

    public SpacecraftFlightViewModel(@NonNull Application application) {

        super(application);
        mSpacecraftFlightRepository = new SpacecraftFlightRepository(application);
        mSpacecraftListLivedata = mSpacecraftFlightRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<SpacecraftFlight>> getLiveData() {

        return mSpacecraftListLivedata;
    }

    public void getSpacecraftFlights(Boolean isConnected) {

        mSpacecraftFlightRepository.getSpacecraftFlightList(isConnected);
    }

    public void getSpacecraftFlightById(int id) {

        mSpacecraftFlightRepository.getSpacecraftFlightById(id);
    }
}
