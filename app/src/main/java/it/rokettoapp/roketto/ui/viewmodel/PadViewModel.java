package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.Pad;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.repository.PadRepository;

public class PadViewModel extends AndroidViewModel {

    private final PadRepository mPadRepository;
    private MutableLiveData<ResponseList<Pad>> mPadListLiveData;

    public PadViewModel(@NonNull Application application) {

        super(application);
        mPadRepository = new PadRepository(application);
        mPadListLiveData = mPadRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<Pad>> getLiveData() {

        return mPadListLiveData;
    }

    public void getPads(Boolean isConnected) {

        mPadRepository.getPadList(isConnected);
    }

    public void getPadById(int id) {

        mPadRepository.getPadById(id);
    }
}
