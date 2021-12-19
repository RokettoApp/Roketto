package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Pad;

@Dao
public interface PadDao extends GenericDao<Integer, Pad> {

    @Override
    @Query("SELECT * FROM pad")
    List<Pad> getAll();

    @Override
    @Query("SELECT * " +
           "FROM pad " +
           "WHERE mId = :id")
    Pad getById(Integer id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<Pad> padList);

    @Override
    @Insert
    void insert(Pad pad);

    @Override
    @Delete
    void delete(Pad pad);

    @Override
    @Query("DELETE FROM pad")
    void deleteAll();
}
