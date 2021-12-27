package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.SpacecraftFlight;

@Dao
public interface SpacecraftFlightDao extends GenericDao<Integer, SpacecraftFlight> {

    @Override
    @Query("SELECT * FROM spacecraft_flight")
    List<SpacecraftFlight> getAll();

    @Override
    @Query("SELECT * " +
           "FROM spacecraft_flight " +
           "WHERE mId = :id")
    SpacecraftFlight getById(Integer id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<SpacecraftFlight> spacecraftFlightList);

    @Override
    @Insert
    void insert(SpacecraftFlight spacecraftFlight);

    @Override
    @Delete
    void delete(SpacecraftFlight spacecraftFlight);

    @Override
    @Query("DELETE FROM spacecraft_flight")
    void deleteAll();
}
