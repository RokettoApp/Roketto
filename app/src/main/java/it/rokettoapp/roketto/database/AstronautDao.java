package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Astronaut;

@Dao
public interface AstronautDao {

    @Query("SELECT * FROM favorite_astronaut")
    List<Astronaut> getAll();

    @Insert
    void insertAstronautList(List<Astronaut> astronautList);

    @Insert
    void insertAstronaut(Astronaut astronaut);

    @Delete
    void delete(Astronaut astronaut);

    @Query("DELETE FROM favorite_astronaut")
    void deleteAll();
}