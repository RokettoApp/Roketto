package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.DockingEvent;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.DockingEventApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DockingEventRepository {

    private static final String TAG = "DockingEventRepository";
    private final DockingEventApiService dockingEventApiService;

    public DockingEventRepository() {

        this.dockingEventApiService = ServiceLocator.getInstance().getDockingEventApiService();
    }

    public void fetchDockingEvents() {

        Call<ResponseList<DockingEvent>> astronautResponseCall = dockingEventApiService.getDockingEvents(5);
        astronautResponseCall.enqueue(new Callback<ResponseList<DockingEvent>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<DockingEvent>> call,
                                   @NonNull Response<ResponseList<DockingEvent>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<DockingEvent> dockingEventList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (DockingEvent dockingEvent : dockingEventList) {
                        debugString.append(dockingEvent.getDockingLocation().getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<DockingEvent>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchDockingEventById(int id) {

        Call<DockingEvent> astronautResponseCall = dockingEventApiService.getDockingEvent(id);
        astronautResponseCall.enqueue(new Callback<DockingEvent>() {

            @Override
            public void onResponse(@NonNull Call<DockingEvent> call,
                                   @NonNull Response<DockingEvent> response) {

                if (response.body() != null && response.isSuccessful()) {
                    DockingEvent dockingEvent = response.body();
                    Log.d(TAG, dockingEvent.getDockingLocation().getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<DockingEvent> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
