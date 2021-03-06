package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AstronautApiService {

    @GET(Constants.ASTRONAUTS_ENDPOINT)
    Call<ResponseList<Astronaut>> getAstronauts(@Query("limit") int limit,
                                                @Query("offset") int offset);

    @GET(Constants.ASTRONAUT_ENDPOINT)
    Call<Astronaut> getAstronaut(@Path("id") int id);
}
