package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.repository.EventRepository;

public class EventViewModel extends AndroidViewModel {

    private EventRepository eventRepository;
    private MutableLiveData<List<Event>> eventMutableLiveData;

    public EventViewModel(@NonNull Application application) {

        super(application);
        eventRepository = new EventRepository(application);
    }

    public MutableLiveData<List<Event>> getEvents() {

        if (eventMutableLiveData == null) fetchEvents();
        return eventMutableLiveData;
    }

    private void fetchEvents() {

        eventMutableLiveData = eventRepository.fetchEvents();
    }

    public void refreshEvents() {

        eventRepository.refreshEvents();
    }
}
