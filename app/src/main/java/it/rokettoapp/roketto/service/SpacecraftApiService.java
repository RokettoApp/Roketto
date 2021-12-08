package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpacecraftApiService {

    @GET(Constants.SPACECRAFTS_ENDPOINT)
    Call<ResponseList<Spacecraft>> getSpacecrafts(@Query("limit") int limit,
                                                  @Query("offset") int offset);

    @GET(Constants.SPACECRAFT_ENDPOINT)
    Call<Spacecraft> getSpacecraft(@Path("id") int id);

    @GET(Constants.SPACECRAFT_FLIGHTS_ENDPOINT)
    Call<ResponseList<SpacecraftFlight>> getSpacecraftFlights(@Query("limit") int limit);

    @GET(Constants.SPACECRAFT_FLIGHT_ENDPOINT)
    Call<SpacecraftFlight> getSpacecraftFlight(@Path("id") int id);
}
