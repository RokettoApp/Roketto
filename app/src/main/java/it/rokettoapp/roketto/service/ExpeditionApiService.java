package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExpeditionApiService {

    @GET(Constants.EXPEDITIONS_ENDPOINT)
    Call<ResponseList<Expedition>> getExpeditions(@Query("limit") int limit);

    @GET(Constants.EXPEDITION_ENDPOINT)
    Call<Expedition> getExpedition(@Path("id") int id);
}
