package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LaunchApiService {

    @GET(Constants.LAUNCHES_ENDPOINT)
    Call<ResponseList<Launch>> getLaunches(@Query("limit") int limit);

    @GET(Constants.LAUNCH_ENDPOINT)
    Call<Launch> getLaunch(@Path("id") String id);

    @GET(Constants.LAUNCHES_PREVIOUS_ENDPOINT)
    Call<ResponseList<Launch>> getPreviousLaunches(@Query("limit") int limit);

    @GET(Constants.LAUNCH_PREVIOUS_ENDPOINT)
    Call<Launch> getPreviousLaunch(@Path("id") String id);

    @GET(Constants.LAUNCHES_UPCOMING_ENDPOINT)
    Call<ResponseList<Launch>> getUpcomingLaunches(@Query("limit") int limit);

    @GET(Constants.LAUNCH_UPCOMING_ENDPOINT)
    Call<Launch> getUpcomingLaunch(@Path("id") String id);
}
