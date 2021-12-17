package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.database.SpacecraftDao;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.service.SpacecraftApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpacecraftRepository {

    private static final String TAG = "SpacecraftRepository";
    private final SpacecraftApiService mSpacecraftApiService;
    private final SpacecraftDao mSpacecraftDao;
    private final DatabaseOperations<Integer, Spacecraft> databaseOperations;
    private final MutableLiveData<List<Spacecraft>> mSpacecraftListLiveData;
    int count;

    public SpacecraftRepository(Application application) {

        this.mSpacecraftApiService = ServiceLocator.getInstance().getSpacecraftApiService();
        mSpacecraftDao = RokettoDatabase.getDatabase(application).spacecraftDao();
        databaseOperations = new DatabaseOperations<>(mSpacecraftDao);
        mSpacecraftListLiveData = new MutableLiveData<>();
        count = 0;
    }

    public MutableLiveData<List<Spacecraft>> getSpacecraftList() {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        databaseOperations.getListFromDatabase(mSpacecraftListLiveData);
//        fetchSpacecrafts();
        return mSpacecraftListLiveData;
    }

    public void refreshSpacecrafts() {

        fetchSpacecrafts();
    }

    private void fetchSpacecrafts() {

        Call<ResponseList<Spacecraft>> spacecraftResponseCall =
                mSpacecraftApiService.getSpacecrafts(5, count);
        spacecraftResponseCall.enqueue(new Callback<ResponseList<Spacecraft>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Spacecraft>> call,
                                   @NonNull Response<ResponseList<Spacecraft>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Spacecraft> spacecraftList = response.body().getResults();
                    mSpacecraftListLiveData.postValue(spacecraftList);
                    databaseOperations.saveList(spacecraftList);
                    Log.d(TAG, "Retrieved " + spacecraftList.size() + " spacecrafts.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Spacecraft>> call,
                                  @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
        count += 5;
    }

    private void fetchSpacecraftById(int id) {

        Call<Spacecraft> spacecraftResponseCall = mSpacecraftApiService.getSpacecraft(id);
        spacecraftResponseCall.enqueue(new Callback<Spacecraft>() {

            @Override
            public void onResponse(@NonNull Call<Spacecraft> call,
                                   @NonNull Response<Spacecraft> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Spacecraft spacecraft = response.body();
                    databaseOperations.saveValue(spacecraft);
                    List<Spacecraft> spacecraftList = new ArrayList<>();
                    spacecraftList.add(spacecraft);
                    mSpacecraftListLiveData.postValue(spacecraftList);
                    Log.d(TAG, spacecraft.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Spacecraft> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchSpacecraftFlights() {

        Call<ResponseList<SpacecraftFlight>> spacecraftResponseCall =
                mSpacecraftApiService.getSpacecraftFlights(5);
        spacecraftResponseCall.enqueue(new Callback<ResponseList<SpacecraftFlight>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<SpacecraftFlight>> call,
                                   @NonNull Response<ResponseList<SpacecraftFlight>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<SpacecraftFlight> spacecraftFlightList = response.body().getResults();
                    Log.d(TAG, "Retrieved " + spacecraftFlightList.size()
                            + " spacecraft flights.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<SpacecraftFlight>> call,
                                  @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void fetchSpacecraftFlightById(int id) {

        Call<SpacecraftFlight> spacecraftResponseCall =
                mSpacecraftApiService.getSpacecraftFlight(id);
        spacecraftResponseCall.enqueue(new Callback<SpacecraftFlight>() {

            @Override
            public void onResponse(@NonNull Call<SpacecraftFlight> call,
                                   @NonNull Response<SpacecraftFlight> response) {

                if (response.body() != null && response.isSuccessful()) {
                    SpacecraftFlight spacecraftFlight = response.body();
                    Log.d(TAG, spacecraftFlight.getDestination());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SpacecraftFlight> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
