package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.repository.AgencyRepository;

public class AgencyViewModel extends AndroidViewModel {

    private final AgencyRepository mAgencyRepository;
    private MutableLiveData<List<Agency>> mAgencyListLivedata;

    public AgencyViewModel(@NonNull Application application) {

        super(application);
        mAgencyRepository = new AgencyRepository(application);
    }

    public MutableLiveData<List<Agency>> getAgencies() {

        if (mAgencyListLivedata == null) mAgencyListLivedata = mAgencyRepository.getAgencyList();

        return mAgencyListLivedata;
    }

    public MutableLiveData<List<Agency>> getAgencyById(int id) {

        if (mAgencyListLivedata == null) mAgencyListLivedata = mAgencyRepository.getAgencyById(id);

        return mAgencyListLivedata;
    }

    public void getNextAgencies(int lastId) {

        mAgencyRepository.getNextAgencies(lastId);
    }

    public void refreshAgencies() {

        mAgencyRepository.refreshAgencies();
    }
}
