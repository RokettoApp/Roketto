package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.AgencyList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AgencyApiService {


    @GET(Constants.AGENCIES_ENDPOINT)
    Call<AgencyList> getAgencies(@Query("limit") int limit);

    @GET(Constants.AGENCY_ENDPOINT)
    Call<Agency> getAgency(@Path("id") int id);
}
