package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Agency;

@Dao
public interface AgencyDao extends GenericDao<Integer, Agency> {

    @Override
    @Query("SELECT * FROM agency")
    List<Agency> getAll();

    @Query("SELECT * " +
           "FROM agency " +
           "WHERE mId > :id " +
           "AND mId < :lastId")
    List<Agency> getAllInRange(int id, int lastId);

    @Override
    @Query("SELECT * " +
           "FROM agency " +
           "WHERE mId = :id")
    Agency getById(Integer id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<Agency> agencyList);

    @Override
    @Insert
    void insert(Agency agency);

    @Override
    @Delete
    void delete(Agency agency);

    @Override
    @Query("DELETE FROM agency")
    void deleteAll();
}
