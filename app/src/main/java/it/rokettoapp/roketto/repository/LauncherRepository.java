package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.LauncherDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Launcher;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.LauncherApiService;
import it.rokettoapp.roketto.util.DatabaseOperations;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LauncherRepository {

    private static final String TAG = "LauncherRepository";
    private final LauncherApiService mLauncherApiService;
    private final LauncherDao mLauncherDao;
    private final DatabaseOperations<Integer, Launcher> databaseOperations;
    private final MutableLiveData<List<Launcher>> mLauncherListLiveData;

    public LauncherRepository(Application application) {

        this.mLauncherApiService = ServiceLocator.getInstance().getLauncherApiService();
        mLauncherDao = RokettoDatabase.getDatabase(application).launcherDao();
        databaseOperations = new DatabaseOperations<>(mLauncherDao);
        mLauncherListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Launcher>> getLiveData() {

        return mLauncherListLiveData;
    }

    public void getLauncherList(Boolean isConnected) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        databaseOperations.getListFromDatabase(mLauncherListLiveData);
//        fetchPrograms();
    }

    public void getLauncherById(int id) {

        // TODO: Aggiungere un controllo sulla data dell'ultima richiesta alle API
        new Thread(() -> {
            Launcher program = mLauncherDao.getById(id);
            if (program != null) {
                List<Launcher> programList = new ArrayList<>();
                programList.add(program);
                mLauncherListLiveData.postValue(programList);
            } else
                fetchLauncherById(id);
        }).start();
    }

    public void clearLaunchers() {

        databaseOperations.deleteAll();
    }

    public void fetchLaunchers() {

        Call<ResponseList<Launcher>> launcherResponseCall = mLauncherApiService.getLaunchers(5);
        launcherResponseCall.enqueue(new Callback<ResponseList<Launcher>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Launcher>> call,
                                   @NonNull Response<ResponseList<Launcher>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Launcher> launcherList = response.body().getResults();
                    Log.d(TAG, "Retrieved " + launcherList + " launchers.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Launcher>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchLauncherById(int id) {

        Call<Launcher> launcherResponseCall = mLauncherApiService.getLauncher(id);
        launcherResponseCall.enqueue(new Callback<Launcher>() {

            @Override
            public void onResponse(@NonNull Call<Launcher> call,
                                   @NonNull Response<Launcher> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Launcher launcher = response.body();
                    Log.d(TAG, launcher.getSerialNumber());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Launcher> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}
