package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Location;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationApiService {


    @GET(Constants.LOCATIONS_ENDPOINT)
    Call<ResponseList<Location>> getLocations(@Query("limit") int limit);

    @GET(Constants.LOCATION_ENDPOINT)
    Call<Location> getLocation(@Path("id") int id);
}
