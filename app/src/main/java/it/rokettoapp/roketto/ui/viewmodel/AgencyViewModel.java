package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.repository.AgencyRepository;

public class AgencyViewModel extends AndroidViewModel {

    private final AgencyRepository mAgencyRepository;
    private MutableLiveData<ResponseList<Agency>> mAgencyListLivedata;

    public AgencyViewModel(@NonNull Application application) {

        super(application);
        mAgencyRepository = new AgencyRepository(application);
        mAgencyListLivedata = mAgencyRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<Agency>> getLiveData() {

        return mAgencyListLivedata;
    }

    public void getAgencies(Boolean isConnected) {

        mAgencyRepository.getAgencyList(isConnected);
    }

    public void getAgencyById(int id) {

        mAgencyRepository.getAgencyById(id);
    }

    public void getNextAgencies(int lastId) {

        mAgencyRepository.getNextAgencies(lastId);
    }

    public void refreshAgencies() {

        mAgencyRepository.refreshAgencies();
    }
}
