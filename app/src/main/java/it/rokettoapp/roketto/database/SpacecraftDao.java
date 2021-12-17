package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Spacecraft;

@Dao
public interface SpacecraftDao extends GenericDao<Integer, Spacecraft> {

    @Override
    @Query("SELECT * FROM spacecraft")
    List<Spacecraft> getAll();

    @Override
    @Query("SELECT * " +
           "FROM spacecraft " +
           "WHERE mId = :id")
    Spacecraft getById(Integer id);

    @Override
    @Insert
    void insertList(List<Spacecraft> spacecraftList);

    @Override
    @Insert
    void insert(Spacecraft spacecraft);

    @Override
    @Delete
    void delete(Spacecraft spacecraft);

    @Override
    @Query("DELETE FROM spacecraft")
    void deleteAll();
}
