package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.repository.EventRepository;

public class EventViewModel extends AndroidViewModel {

    private final EventRepository mEventRepository;
    private MutableLiveData<ResponseList<Event>> mEventMutableLiveData;
    private boolean isLoading;

    public EventViewModel(@NonNull Application application) {

        super(application);
        mEventRepository = new EventRepository(application);
        mEventMutableLiveData = mEventRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<Event>> getLiveData() {

        return mEventMutableLiveData;
    }

    public void getEvents(Boolean isConnected) {

        mEventRepository.getEventList(isConnected);
    }

    public void getNewEvents(){
        mEventRepository.fetchNewEvents();
    }

    public void getEventById(int id) {

        mEventRepository.getEventById(id);
    }

    public void refreshEvents() {

        mEventRepository.refreshEvents();
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
