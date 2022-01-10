package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.DockingEvent;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.repository.DockingEventRepository;

public class DockingEventViewModel extends AndroidViewModel {

    private final DockingEventRepository mDockingEventRepository;
    private final MutableLiveData<ResponseList<DockingEvent>> mDockingEventListLiveData;

    public DockingEventViewModel(@NonNull Application application) {

        super(application);
        mDockingEventRepository = new DockingEventRepository(application);
        mDockingEventListLiveData = mDockingEventRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<DockingEvent>> getLiveData() {

        return mDockingEventListLiveData;
    }

    public void getDockingEvents(Boolean isConnected) {

        mDockingEventRepository.getDockingEventList(isConnected);
    }

    public void getDockingEventById(int id) {

        mDockingEventRepository.getDockingEventById(id);
    }
}
