package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpacecraftFlightApiService {

    @GET(Constants.SPACECRAFT_FLIGHTS_ENDPOINT)
    Call<ResponseList<SpacecraftFlight>> getSpacecraftFlights(@Query("limit") int limit);

    @GET(Constants.SPACECRAFT_FLIGHT_ENDPOINT)
    Call<SpacecraftFlight> getSpacecraftFlight(@Path("id") int id);
}
