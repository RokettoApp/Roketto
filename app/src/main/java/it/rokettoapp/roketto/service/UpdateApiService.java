package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.Update;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UpdateApiService {

    @GET(Constants.UPDATES_ENDPOINT)
    Call<ResponseList<Update>> getUpdates(@Query("limit") int limit);

    @GET(Constants.UPDATE_ENDPOINT)
    Call<Update> getUpdate(@Path("id") int id);
}
