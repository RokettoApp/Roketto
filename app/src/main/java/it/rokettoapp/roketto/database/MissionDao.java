package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Mission;

@Dao
public interface MissionDao {
    @Query("SELECT * FROM favorite_mission")
    List<Mission> getAll();

    @Insert
    void insertAgencyList(List<Mission> missionList);

    @Insert
    void insertAgency(Mission mission);

    @Delete
    void delete(Mission mission);

    @Query("DELETE FROM favorite_mission")
    void deleteAll();
}
