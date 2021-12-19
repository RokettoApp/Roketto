package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Astronaut;

@Dao
public interface AstronautDao extends GenericDao<Integer, Astronaut> {

    @Override
    @Query("SELECT * FROM astronaut")
    List<Astronaut> getAll();

    @Override
    @Query("SELECT * " +
           "FROM astronaut " +
           "WHERE mId = :id")
    Astronaut getById(Integer id);

    @Override
    @Insert
    void insertList(List<Astronaut> astronautList);

    @Override
    @Insert
    void insert(Astronaut astronaut);

    @Override
    @Delete
    void delete(Astronaut astronaut);

    @Override
    @Query("DELETE FROM astronaut")
    void deleteAll();
}