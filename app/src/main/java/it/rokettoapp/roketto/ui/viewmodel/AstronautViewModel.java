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
        mAstronautListLivedata = mAstronautRepository.getLiveData();
    }

    public MutableLiveData<List<Astronaut>> getLiveData() {

        return mAstronautListLivedata;
    }

    public void getAstronauts() {

        mAstronautRepository.getAstronautList();
    }

    public void getAstronautById(int id) {

        mAstronautRepository.getAstronautById(id);
    }

    public void getAstronautsByIds(List<Integer> ids){
        mAstronautRepository.getAstronautsByIds(ids);
    }

    public void refreshAstronauts() {

        mAstronautRepository.refreshAstronauts();
    }
}
