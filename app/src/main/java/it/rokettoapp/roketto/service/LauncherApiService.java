package it.rokettoapp.roketto.service;

import it.rokettoapp.roketto.model.Launcher;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LauncherApiService {

    @GET(Constants.LAUNCHERS_ENDPOINT)
    Call<ResponseList<Launcher>> getLaunchers(@Query("limit") int limit);

    @GET(Constants.LAUNCHER_ENDPOINT)
    Call<Launcher> getLauncher(@Path("id") int id);
}
