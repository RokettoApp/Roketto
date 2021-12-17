package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.MissionPatch;

@Dao
public interface MissionPatchDao extends GenericDao<Integer, MissionPatch> {

    @Override
    @Query("SELECT * FROM mission_patch")
    List<MissionPatch> getAll();

    @Override
    @Query("SELECT * " +
           "FROM mission_patch " +
           "WHERE mId = :id")
    MissionPatch getById(Integer id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<MissionPatch> missionPatchList);

    @Override
    @Insert
    void insert(MissionPatch missionPatch);

    @Override
    @Delete
    void delete(MissionPatch missionPatch);

    @Override
    @Query("DELETE FROM mission_patch")
    void deleteAll();
}
