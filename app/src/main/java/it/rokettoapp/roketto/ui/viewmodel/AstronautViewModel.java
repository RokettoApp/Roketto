package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.repository.AstronautRepository;

public class AstronautViewModel extends AndroidViewModel {

    private final AstronautRepository mAstronautRepository;
    private MutableLiveData<ResponseList<Astronaut>> mAstronautListLivedata;

    private boolean isLoading;


    public AstronautViewModel(@NonNull Application application) {

        super(application);
        mAstronautRepository = new AstronautRepository(application);
        mAstronautListLivedata = mAstronautRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<Astronaut>> getLiveData() {

        return mAstronautListLivedata;
    }

    public void getAstronauts(Boolean isConnected) {

        mAstronautRepository.getAstronautList(isConnected);
    }

    public void getNewAstronauts() {

        mAstronautRepository.getNewAstronautList();
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

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
