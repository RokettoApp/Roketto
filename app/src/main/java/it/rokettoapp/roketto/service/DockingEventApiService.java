package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.DockingEvent;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DockingEventApiService {

    @GET(Constants.DOCKING_EVENTS_ENDPOINT)
    Call<ResponseList<DockingEvent>> getDockingEvents(@Query("limit") int limit);

    @GET(Constants.DOCKING_EVENT_ENDPOINT)
    Call<DockingEvent> getDockingEvent(@Path("id") int id);
}
