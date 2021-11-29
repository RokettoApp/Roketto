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
    void insertSpacecraftList(List<Spacecraft> spacecraftList);

    @Insert
    void insertSpacecraft(Spacecraft spacecraft);

    @Delete
    void delete(Spacecraft spacecraft);

    @Query("DELETE FROM favorite_spacecraft")
    void deleteAll();
}
