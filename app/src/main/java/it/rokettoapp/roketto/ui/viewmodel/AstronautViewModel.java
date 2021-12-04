package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.repository.AstronautRepository;

public class AstronautViewModel extends AndroidViewModel {

    private final AstronautRepository mAstronautRepository;
    private MutableLiveData<List<Astronaut>> mAstronautListLivedata;

    public AstronautViewModel(@NonNull Application application) {

        super(application);
        mAstronautRepository = new AstronautRepository(application);
    }

    public MutableLiveData<List<Astronaut>> getAstronauts() {

        if (mAstronautListLivedata == null) fetchAstronauts();
        return mAstronautListLivedata;
    }

    private void fetchAstronauts() {

        mAstronautListLivedata = mAstronautRepository.getAstronautList();
    }

    public void refreshAstronauts() {

        mAstronautRepository.refreshAstronauts();
    }
}
