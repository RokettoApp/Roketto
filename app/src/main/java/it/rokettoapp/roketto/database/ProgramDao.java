package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Program;

@Dao
public interface ProgramDao extends GenericDao<Integer, Program> {

    @Override
    @Query("SELECT * FROM program")
    List<Program> getAll();

    @Override
    @Query("SELECT * " +
           "FROM program " +
           "WHERE mId = :id")
    Program getById(Integer id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<Program> programList);

    @Override
    @Insert
    void insert(Program program);

    @Override
    @Delete
    void delete(Program program);

    @Override
    @Query("DELETE FROM program")
    void deleteAll();
}
