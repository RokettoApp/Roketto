package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Location;

@Dao
public interface LocationDao extends GenericDao<Integer, Location> {

    @Override
    @Query("SELECT * FROM location")
    List<Location> getAll();

    @Override
    @Query("SELECT * " +
           "FROM location " +
           "WHERE mId = :id")
    Location getById(Integer id);

    @Override
    @Insert
    void insertList(List<Location> locationList);

    @Override
    @Insert
    void insert(Location location);

    @Override
    @Delete
    void delete(Location location);

    @Override
    @Query("DELETE FROM location")
    void deleteAll();
}
