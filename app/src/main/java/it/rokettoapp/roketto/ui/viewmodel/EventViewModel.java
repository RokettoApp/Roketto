package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.repository.EventRepository;

public class EventViewModel extends AndroidViewModel {

    private final EventRepository mEventRepository;
    private MutableLiveData<List<Event>> mEventMutableLiveData;
    private boolean isLoading;

    public EventViewModel(@NonNull Application application) {

        super(application);
        mEventRepository = new EventRepository(application);
        mEventMutableLiveData = mEventRepository.getLiveData();
    }

    public MutableLiveData<List<Event>> getLiveData() {

        return mEventMutableLiveData;
    }

    public void getEvents(Boolean isConnected) {

        mEventRepository.getEventList(isConnected);
    }

    public void updateFavouriteEvent(Event event){
        mEventRepository.updateFavouriteEvent(event);
    }

    public void getFavoritesEvents(){
        mEventRepository.getFavoritesEvent();
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
