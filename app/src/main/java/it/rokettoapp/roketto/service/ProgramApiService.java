package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProgramApiService {

    @GET(Constants.PROGRAMS_ENDPOINT)
    Call<ResponseList<Program>> getPrograms(@Query("limit") int limit);

    @GET(Constants.PROGRAM_ENDPOINT)
    Call<Program> getProgram(@Path("id") int id);
}
