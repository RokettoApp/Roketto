package it.rokettoapp.roketto.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.service.ProgramApiService;
import it.rokettoapp.roketto.util.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramRepository {

    private static final String TAG = "ProgramRepository";
    private final ProgramApiService programApiService;

    public ProgramRepository() {

        this.programApiService = ServiceLocator.getInstance().getProgramApiService();
    }

    public void fetchPrograms() {

        Call<ResponseList<Program>> programResponseCall = programApiService.getPrograms(5);
        programResponseCall.enqueue(new Callback<ResponseList<Program>>() {

            @Override
            public void onResponse(@NonNull Call<ResponseList<Program>> call,
                                   @NonNull Response<ResponseList<Program>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    List<Program> programList = response.body().getResults();
                    StringBuilder debugString = new StringBuilder();
                    for (Program program : programList) {
                        debugString.append(program.getName()).append(" --- ");
                    }
                    Log.d(TAG, debugString.toString());
                } else {
                    Log.e(TAG, "Request failed.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList<Program>> call, @NonNull Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void fetchProgramById(int id) {

        Call<Program> programResponseCall = programApiService.getProgram(id);
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
}
