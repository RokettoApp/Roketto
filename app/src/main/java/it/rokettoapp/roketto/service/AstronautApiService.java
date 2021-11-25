package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.AstronautList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AstronautApiService {

    @GET(Constants.ASTRONAUTS_ENDPOINT)
    Call<AstronautList> getAstronauts(@Query("limit") int limit);

    @GET(Constants.ASTRONAUT_ENDPOINT)
    Call<Astronaut> getAstronaut(@Path("id") int id);
}
