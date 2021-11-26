package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Spacecraft;

@Dao
public interface SpacecraftDao {
    @Query("SELECT * FROM favorite_spacecraft")
    List<Spacecraft> getAll();

    @Insert
    void insertAgencyList(List<Spacecraft> spacecraftList);

    @Insert
    void insertAgency(Spacecraft spacecraft);

    @Delete
    void delete(Spacecraft spacecraft);

    @Delete
    void deleteAll();
}
