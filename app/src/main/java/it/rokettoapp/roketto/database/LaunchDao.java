package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Launch;

@Dao
public interface LaunchDao extends GenericDao<String, Launch> {

    @Override
    @Query("SELECT * FROM launch")
    List<Launch> getAll();

    @Override
    @Query("SELECT * " +
           "FROM launch " +
           "WHERE mId = :id")
    Launch getById(String id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<Launch> launchList);

    @Override
    @Insert
    void insert(Launch launch);

    @Override
    @Delete
    void delete(Launch launch);

    @Override
    @Query("DELETE FROM launch")
    void deleteAll();
}
