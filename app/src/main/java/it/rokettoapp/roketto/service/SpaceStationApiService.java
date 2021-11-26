package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpaceStationApiService {

    @GET(Constants.SPACESTATIONS_ENDPOINT)
    Call<ResponseList<SpaceStation>> getSpaceStations(@Query("limit") int limit);

    @GET(Constants.SPACESTATION_ENDPOINT)
    Call<SpaceStation> getSpaceStation(@Path("id") int id);
}
