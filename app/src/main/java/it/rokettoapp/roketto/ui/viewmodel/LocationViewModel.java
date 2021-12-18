package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Location;
import it.rokettoapp.roketto.repository.LocationRepository;

public class LocationViewModel extends AndroidViewModel {

    private final LocationRepository mLocationRepository;
    private MutableLiveData<List<Location>> mLocationListLivedata;

    public LocationViewModel(@NonNull Application application) {

        super(application);
        mLocationRepository = new LocationRepository(application);
        mLocationListLivedata = mLocationRepository.getLiveData();
    }

    public MutableLiveData<List<Location>> getLiveData() {

        return mLocationListLivedata;
    }

    public void getLocations() {

        mLocationRepository.getLocationList();
    }

    public void getLocationById(int id) {

        mLocationRepository.getLocationById(id);
    }
}
