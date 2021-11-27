package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.EventApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private static final String TAG = "EventRepository";
    private final EventApiService eventApiService;

    public EventRepository() {

        this.eventApiService = ServiceLocator.getsInstance().getEventApiService();
    }

    public void fetchEvents() {

        Call<ResponseList<Event>> eventResponseCall = eventApiService.getEvents(5);
        eventResponseCall.enqueue(new Callback<ResponseList<Event>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Event>> call,
                                   @NonNull Response<ResponseList<Event>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Event> eventList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Event event : eventList) {
                        debugString.append(event.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Event>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchEventById(int id) {

        Call<Event> eventResponseCall = eventApiService.getEvent(id);
        eventResponseCall.enqueue(new Callback<Event>() {

            @Override
            public void onResponse(@NonNull Call<Event> call,
                                   @NonNull Response<Event> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Event event = response.body();
                    Log.d(TAG, event.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchPreviousEvents() {

        Call<ResponseList<Event>> eventResponseCall = eventApiService.getPreviousEvents(5);
        eventResponseCall.enqueue(new Callback<ResponseList<Event>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Event>> call,
                                   @NonNull Response<ResponseList<Event>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Event> eventList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Event event : eventList) {
                        debugString.append(event.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Event>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchPreviousEventById(int id) {

        Call<Event> eventResponseCall = eventApiService.getPreviousEvent(id);
        eventResponseCall.enqueue(new Callback<Event>() {

            @Override
            public void onResponse(@NonNull Call<Event> call,
                                   @NonNull Response<Event> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Event event = response.body();
                    Log.d(TAG, event.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
    public void fetchUpcomingEvents() {

        Call<ResponseList<Event>> eventResponseCall = eventApiService.getUpcomingEvents(5);
        eventResponseCall.enqueue(new Callback<ResponseList<Event>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Event>> call,
                                   @NonNull Response<ResponseList<Event>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Event> eventList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Event event : eventList) {
                        debugString.append(event.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Event>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchUpcomingEventById(int id) {

        Call<Event> eventResponseCall = eventApiService.getUpcomingEvent(id);
        eventResponseCall.enqueue(new Callback<Event>() {

            @Override
            public void onResponse(@NonNull Call<Event> call,
                                   @NonNull Response<Event> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Event event = response.body();
                    Log.d(TAG, event.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
