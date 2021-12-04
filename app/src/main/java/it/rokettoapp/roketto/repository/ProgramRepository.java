package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.database.ProgramDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.ProgramApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramRepository {

    private static final String TAG = "ProgramRepository";
    private final ProgramApiService mProgramApiService;
    private final ProgramDao mProgramDao;
    private final MutableLiveData<List<Program>> mProgramListLiveData;
    int count;

    public ProgramRepository(Application application) {

        this.mProgramApiService = ServiceLocator.getInstance().getProgramApiService();
        mProgramDao = RokettoDatabase.getDatabase(application).programDao();
        mProgramListLiveData = new MutableLiveData<>();
        count = 0;
    }

    public MutableLiveData<List<Program>> getProgramList() {

        getProgramsFromApi();
        return mProgramListLiveData;
    }

    public void refreshPrograms() {

        getProgramsFromApi();
    }

    private void getProgramsFromApi() {

        Call<ResponseList<Program>> programResponseCall =
                mProgramApiService.getPrograms(5, count);
        programResponseCall.enqueue(new Callback<ResponseList<Program>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Program>> call,
                                   @NonNull Response<ResponseList<Program>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Program> programList = response.body().getResults();
                    mProgramListLiveData.postValue(programList);
                    saveOnDatabase(programList);
                    Log.d(TAG, "Retrieved " + programList.size() + " programs.");
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Program>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
        count += 5;
    }

    public void fetchProgramById(int id) {

        Call<Program> programResponseCall = mProgramApiService.getProgram(id);
        programResponseCall.enqueue(new Callback<Program>() {

            @Override
            public void onResponse(@NonNull Call<Program> call,
                                   @NonNull Response<Program> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Program program = response.body();
                    Log.d(TAG, program.getName());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Program> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void saveOnDatabase(List<Program> programList) {

        RokettoDatabase.databaseWriteExecutor.execute(() -> {
            mProgramDao.deleteAll();
            mProgramDao.insertProgramList(programList);
        });
    }

    public List<Program> getProgramsFromDatabase() {

        List<Program> programList = new ArrayList<>();
        Runnable runnable = () -> {

            List<Program> results = mProgramDao.getAll();
            if (results != null)
                programList.addAll(results);
        };
        new Thread(runnable).start();
        return programList;
    }
}
