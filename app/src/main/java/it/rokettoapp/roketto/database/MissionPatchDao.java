package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.MissionPatch;

@Dao
public interface MissionPatchDao {

    @Query("SELECT * FROM mission_patch")
    List<MissionPatch> getAll();

    @Query("SELECT * " +
           "FROM mission_patch " +
           "WHERE mId = :id")
    MissionPatch getById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMissionPathList(List<MissionPatch> missionPatchList);

    @Insert
    void insertMissionPatch(MissionPatch missionPatch);

    @Delete
    void delete(MissionPatch missionPatch);

    @Query("DELETE FROM mission_patch")
    void deleteAll();
}
