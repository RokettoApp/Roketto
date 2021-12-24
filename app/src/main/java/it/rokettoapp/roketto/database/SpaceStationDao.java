package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.SpaceStation;

@Dao
public interface SpaceStationDao extends GenericDao<Integer, SpaceStation> {

    @Override
    @Query("SELECT * FROM spacestation")
    List<SpaceStation> getAll();

    @Override
    @Query("SELECT * " +
           "FROM spacestation " +
           "WHERE mId = :id")
    SpaceStation getById(Integer id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<SpaceStation> spaceStationList);

    @Override
    @Insert
    void insert(SpaceStation spaceStation);

    @Override
    @Delete
    void delete(SpaceStation spaceStation);

    @Override
    @Query("DELETE FROM program")
    void deleteAll();
}
