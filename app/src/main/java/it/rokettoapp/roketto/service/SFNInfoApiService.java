package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.SFNInfo;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SFNInfoApiService {

    @GET(Constants.INFO_ENDPOINT)
    Call<SFNInfo> getInfo();
}
