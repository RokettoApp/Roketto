package it.rokettoapp.roketto.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.database.MissionPatchDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.MissionPatch;
import it.rokettoapp.roketto.util.DatabaseOperations;

public class MissionPatchRepository {

    private static final String TAG = "ExpeditionRepository";
    private final MissionPatchDao mMissionPatchDao;
    private final DatabaseOperations<Integer, MissionPatch> databaseOperations;
    private final MutableLiveData<List<MissionPatch>> mMissionPatchLiveData;

    public MissionPatchRepository(Application application) {

        this.mMissionPatchDao = RokettoDatabase.getDatabase(application).missionPatchDao();
        this.databaseOperations = new DatabaseOperations<>(mMissionPatchDao);
        this.mMissionPatchLiveData = new MutableLiveData<>();
    }

    private void saveMissionPatchListOnDatabase(List<MissionPatch> missionPatchList) {

        databaseOperations.saveList(missionPatchList);
    }

    private void saveMissionPatchOnDatabase(MissionPatch missionPatch) {

        databaseOperations.saveValue(missionPatch);
    }

    private void getExpeditionsFromDatabase() {

        databaseOperations.getListFromDatabase(mMissionPatchLiveData);
    }
}
