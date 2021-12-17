package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Expedition;

@Dao
public interface ExpeditionDao extends GenericDao<Integer, Expedition> {

    @Override
    @Query("SELECT * FROM expedition")
    List<Expedition> getAll();

    @Override
    @Query("SELECT * " +
           "FROM expedition " +
           "WHERE mId = :id")
    Expedition getById(Integer id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<Expedition> expeditionList);

    @Override
    @Insert
    void insert(Expedition expedition);

    @Override
    @Delete
    void delete(Expedition expedition);

    @Override
    @Query("DELETE FROM expedition")
    void deleteAll();
}
