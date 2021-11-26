package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Agency;

@Dao
public interface AgencyDao {
    @Query("SELECT * FROM favorite_agencie")
    List<Agency> getAll();

    @Insert
    void insertAgencyList(List<Agency> agencyList);

    @Insert
    void insertAgency(Agency agency);

    @Delete
    void delete(Agency agency);

    @Delete
    void deleteAll();
}