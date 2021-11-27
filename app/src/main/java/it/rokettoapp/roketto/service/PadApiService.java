package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Pad;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PadApiService {


    @GET(Constants.PADS_ENDPOINT)
    Call<ResponseList<Pad>> getPads(@Query("limit") int limit);

    @GET(Constants.PAD_ENDPOINT)
    Call<Pad> getPad(@Path("id") int id);
}
