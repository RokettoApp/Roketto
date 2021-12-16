package it.rokettoapp.roketto.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.database.MissionPatchDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.MissionPatch;

public class MissionPatchRepository {

    private static final String TAG = "ExpeditionRepository";
    private final MissionPatchDao mMissionPatchDao;
    private final MutableLiveData<List<MissionPatch>> mMissionPatchLiveData;

    public MissionPatchRepository(Application application) {

        this.mMissionPatchDao = RokettoDatabase.getDatabase(application).missionPatchDao();
        this.mMissionPatchLiveData = new MutableLiveData<>();
    }

    private void saveMissionPatchListOnDatabase(List<MissionPatch> missionPatchList) {

        RokettoDatabase.databaseWriteExecutor.execute(() ->
                mMissionPatchDao.insertMissionPathList(missionPatchList));
    }

    private void saveMissionPatchOnDatabase(MissionPatch missionPatch) {

        RokettoDatabase.databaseWriteExecutor.execute(() ->
                mMissionPatchDao.insertMissionPatch(missionPatch));
    }

    private void getExpeditionsFromDatabase() {

        new Thread(() -> mMissionPatchLiveData.postValue(mMissionPatchDao.getAll())).start();
    }
}
