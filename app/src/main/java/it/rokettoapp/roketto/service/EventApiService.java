package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventApiService {

    @GET(Constants.EVENTS_ENDPOINT)
    Call<ResponseList<Event>> getEvents(@Query("limit") int limit, @Query("offset") int offset);

    @GET(Constants.EVENT_ENDPOINT)
    Call<Event> getEvent(@Path("id") int id);

    @GET(Constants.EVENTS_PREVIOUS_ENDPOINT)
    Call<ResponseList<Event>> getPreviousEvents(@Query("limit") int limit);

    @GET(Constants.EVENT_PREVIOUS_ENDPOINT)
    Call<Event> getPreviousEvent(@Path("id") int id);

    @GET(Constants.EVENTS_UPCOMING_ENDPOINT)
    Call<ResponseList<Event>> getUpcomingEvents(@Query("limit") int limit);

    @GET(Constants.EVENT_UPCOMING_ENDPOINT)
    Call<Event> getUpcomingEvent(@Path("id") int id);
}
