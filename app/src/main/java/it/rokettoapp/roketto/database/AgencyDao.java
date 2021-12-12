package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Agency;

@Dao
public interface AgencyDao {

    @Query("SELECT * FROM agency")
    List<Agency> getAll();

    @Query("SELECT * " +
           "FROM agency " +
           "WHERE mId > :id " +
           "AND mId < :lastId")
    List<Agency> getAllInRange(int id, int lastId);

    @Query("SELECT * " +
           "FROM agency " +
           "WHERE mId = :id")
    Agency getById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAgencyList(List<Agency> agencyList);

    @Insert
    void insertAgency(Agency agency);

    @Delete
    void delete(Agency agency);

    @Query("DELETE FROM agency")
    void deleteAll();
}
