package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Program;

@Dao
public interface ProgramDao {
    @Query("SELECT * FROM program")
    List<Program> getAll();

    @Insert
    void insertProgramList(List<Program> programList);

    @Insert
    void insertProgram(Program program);

    @Delete
    void delete(Program program);

    @Query("DELETE FROM program")
    void deleteAll();
}
