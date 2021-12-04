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

    public EventViewModel(@NonNull Application application) {

        super(application);
        mEventRepository = new EventRepository(application);
    }

    public MutableLiveData<List<Event>> getEvents() {

        if (mEventMutableLiveData == null) fetchEvents();
        return mEventMutableLiveData;
    }

    private void fetchEvents() {

        mEventMutableLiveData = mEventRepository.getEventList();
    }

    public void refreshEvents() {

        mEventRepository.refreshEvents();
    }
}
